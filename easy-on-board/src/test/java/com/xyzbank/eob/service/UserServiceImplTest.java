package com.xyzbank.eob.service;

import com.xyzbank.eob.configuration.AppConfiguration;
import com.xyzbank.eob.dto.ValidationDTO;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.dto.response.UserRegisterResponse;
import com.xyzbank.eob.exception.BusinessException;
import com.xyzbank.eob.exception.UserRegistrationException;
import com.xyzbank.eob.exception.UsernameExistsException;
import com.xyzbank.eob.model.Address;
import com.xyzbank.eob.model.Country;
import com.xyzbank.eob.model.Role;
import com.xyzbank.eob.repository.RoleRepository;
import com.xyzbank.eob.util.DateConversionService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.xyzbank.eob.GlobalConstants.ROLE_USER;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.xyzbank.eob.repository.UserRepository;
import com.xyzbank.eob.repository.CountryRepository;
import com.xyzbank.eob.repository.AddressRepository;
import com.xyzbank.eob.service.impl.UserServiceImpl;
import com.xyzbank.eob.model.User;
import com.xyzbank.eob.dto.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private UploadService uploadService;
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private User newUser;
    @Mock
    private AccountService accountService;
    @Mock
    private AppConfiguration appConfiguration;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser() throws Exception {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("newUser");
        userRequest.setDob("26/10/2001");
        userRequest.setCountry("Netherlands");
        userRequest.setAddress("testing address");
        userRequest.setName("testuser");
        userRequest.setDocumentType("testDocumentType");

        MultipartFile document = mock(MultipartFile.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate mockDate = LocalDate.parse("26/10/2001", formatter);
        Role role = new Role();
        role.setName(ROLE_USER);
        role.setId(1l);

        ValidationDTO<UserRequest> validationDTO = new ValidationDTO();
        validationDTO.setRequestDTO(userRequest);
        validationDTO.setValidationErrors(Collections.EMPTY_MAP);

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(countryRepository.findByName("Netherlands")).thenReturn(Optional.of(new Country()));
        when(addressRepository.save(any(Address.class))).thenReturn(new Address());
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
        when(validationService.validate(any())).thenReturn(validationDTO);
        // Act
        GenericResponse<UserRegisterResponse> response = userService.register(userRequest, document);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        verify(userRepository).save(any(User.class));
        verify(uploadService).upload(document, newUser);
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("existingUser");
        userRequest.setDob("26/10/2001");
        userRequest.setCountry("Netherlands");
        userRequest.setAddress("testing address");

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(UsernameExistsException.class, () -> userService.register(userRequest, mock(MultipartFile.class)));
    }

    @Test
    void testRegisterUser_EmptyDocument() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("newUser");
        userRequest.setDob("26/10/2001");
        userRequest.setCountry("Netherlands");
        userRequest.setAddress("testing address");
        Country country = new Country();
        country.setCountryCode("NL");
        country.setName("Netherlands");


        MultipartFile emptyFile = mock(MultipartFile.class);
        when(emptyFile.isEmpty()).thenReturn(true);

        // Act & Assert
        assertThrows(BusinessException.class, () -> userService.register(userRequest, emptyFile));
    }

    @Test
    void testRegisterUser_InvalidFile() {

        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("newUser");
        userRequest.setDob("26/10/2001");
        userRequest.setCountry("Netherlands");
        userRequest.setAddress("testing address");
        userRequest.setName("testuser");
        userRequest.setDocumentType("testDocumentType");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate mockDate = LocalDate.parse("26/10/2001", formatter);
        Role role = new Role();
        role.setName(ROLE_USER);
        role.setId(1l);

        ValidationDTO<UserRequest> validationDTO = new ValidationDTO();
        validationDTO.setRequestDTO(userRequest);
        validationDTO.setValidationErrors(Collections.EMPTY_MAP);

        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        // Act & Assert
        assertThrows(BusinessException.class, () -> userService.register(userRequest, null));
    }

    @Test
    void testRegisterUser_UnderAge() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("newUser");
        userRequest.setDob("26/10/2010");
        userRequest.setCountry("Netherlands");
        userRequest.setAddress("testing address");
        userRequest.setName("testuser");
        userRequest.setDocumentType("testDocumentType");

        MultipartFile document = mock(MultipartFile.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate mockDate = LocalDate.parse("26/10/2001", formatter);
        Role role = new Role();
        role.setName(ROLE_USER);
        role.setId(1l);

        ValidationDTO<UserRequest> validationDTO = new ValidationDTO();
        validationDTO.setRequestDTO(userRequest);
        validationDTO.setValidationErrors(Collections.EMPTY_MAP);
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(validationService.validate(any())).thenReturn(validationDTO);
        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        // Act & Assert
        assertThrows(UserRegistrationException.class, () -> userService.register(userRequest, mock(MultipartFile.class)));
    }

}
