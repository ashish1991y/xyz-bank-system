package com.xyzbank.eob.service.impl;

import com.xyzbank.eob.configuration.AppConfiguration;
import com.xyzbank.eob.constants.Status;
import com.xyzbank.eob.dto.ValidationDTO;
import com.xyzbank.eob.dto.request.UserRequest;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.dto.response.UserRegisterResponse;
import com.xyzbank.eob.exception.BusinessException;
import com.xyzbank.eob.exception.UserRegistrationException;
import com.xyzbank.eob.exception.UsernameExistsException;
import com.xyzbank.eob.model.Account;
import com.xyzbank.eob.model.Address;
import com.xyzbank.eob.model.Country;
import com.xyzbank.eob.model.User;
import com.xyzbank.eob.repository.AddressRepository;
import com.xyzbank.eob.repository.CountryRepository;
import com.xyzbank.eob.repository.RoleRepository;
import com.xyzbank.eob.repository.UserRepository;
import com.xyzbank.eob.service.AccountService;
import com.xyzbank.eob.service.UploadService;
import com.xyzbank.eob.service.UserService;
import com.xyzbank.eob.service.ValidationService;
import com.xyzbank.eob.util.DateConversionService;
import com.xyzbank.eob.util.PasswordGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Collections;

import static com.xyzbank.eob.GlobalConstants.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final UploadService uploadService;
    private final AddressRepository addressRepository;
    private final AccountService accountService;
    private final AppConfiguration appConfiguration;
    private final RoleRepository roleRepository;
    private final ValidationService validationService;

    @Override
    @Transactional
    public GenericResponse<UserRegisterResponse> register(UserRequest userRequest, MultipartFile document) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new UsernameExistsException("Must provide Unique User Name");
        }
        if(document == null || document.isEmpty()){
            throw new BusinessException("", org.springframework.http.HttpStatus.BAD_REQUEST.value(),"Document should not be empty");
        }
        ValidationDTO<UserRequest> validate = validationService.validate(userRequest);
        if(!validate.getValidationErrors().isEmpty()){
            return buildValidationErrorResponse(validate);
        }

        validateUniqueUsername(userRequest.getUsername());
        LocalDate dateOfBirth = validateAge(userRequest.getDob());
        Country country = validateCountry(userRequest.getCountry());

        Address savedAddress = saveAddress(userRequest.getAddress(), country);
        User newUser = createUser(userRequest, dateOfBirth, savedAddress);
        User savedUser = userRepository.save(newUser);
        if(savedUser!=null && savedUser.getId()!=null){
            uploadService.upload(document, savedUser);
        }
        return buildResponse(savedUser);
    }

    private void validateUniqueUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameExistsException("Must provide Unique User Name");
        }
    }

    private LocalDate validateAge(String dob) {
        LocalDate dateOfBirth = DateConversionService.convertStringToLocalDate(dob);
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new UserRegistrationException("Only customers above 18 years of age are allowed to register.");
        }
        return dateOfBirth;
    }

    private Country validateCountry(String countryName) {
        return countryRepository.findByName(countryName)
                .orElseThrow(() -> new UserRegistrationException("Registration is only allowed for customers from supported countries."));
    }

    private Address saveAddress(String streetAddress, Country country) {
        Address address = new Address();
        address.setStreetAddress(streetAddress);
        address.setCountry(country);
        address.setStatus(appConfiguration.getStatusMasterMap().get(Status.ACTIVE.toString()));
        return addressRepository.save(address);
    }

    private User createUser(UserRequest userRequest, LocalDate dateOfBirth, Address savedAddress) {
        User newUser = new User();
        newUser.setName(userRequest.getName());
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(PasswordGenerator.generateRandomPassword());
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setAddresses(Collections.singleton(savedAddress));
        newUser.setStatus(appConfiguration.getStatusMasterMap().get(Status.ACTIVE.toString()));
        newUser.setRoles(Collections.singleton(roleRepository.findByName(ROLE_USER).get()));
        Account account = accountService.createAccountForUser(newUser);
        newUser.setAccounts(Collections.singleton(account));
        return newUser;
    }

    private GenericResponse<UserRegisterResponse> buildResponse(User savedUser) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse(savedUser.getUsername(), savedUser.getPassword());
        return GenericResponse.<UserRegisterResponse>builder()
                .data(Arrays.asList(userRegisterResponse))
                .statusCode(HttpStatus.SC_CREATED)
                .build();
    }

    private GenericResponse<UserRegisterResponse> buildValidationErrorResponse(ValidationDTO<UserRequest> validationDTO) {
        return GenericResponse.<UserRegisterResponse>builder()
                .data(Collections.emptyList())
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .validationErrors(validationDTO.getValidationErrors())
                .build();
    }
}
