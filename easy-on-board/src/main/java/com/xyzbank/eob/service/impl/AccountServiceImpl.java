package com.xyzbank.eob.service.impl;

import com.xyzbank.eob.configuration.AppConfiguration;
import com.xyzbank.eob.constants.Status;
import com.xyzbank.eob.dto.response.AccountOverview;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.exception.AccountCreationException;
import com.xyzbank.eob.model.Account;
import com.xyzbank.eob.model.AccountType;
import com.xyzbank.eob.model.Currency;
import com.xyzbank.eob.model.User;
import com.xyzbank.eob.repository.AccountRepository;
import com.xyzbank.eob.repository.UserRepository;
import com.xyzbank.eob.service.AccountService;
import com.xyzbank.eob.util.IBANGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.xyzbank.eob.GlobalConstants.ACCOUNT_TYPE_SAVING;
import static com.xyzbank.eob.GlobalConstants.CURRENCY_TYPE_EURO;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AppConfiguration appConfiguration;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Account createAccountForUser(User user) throws AccountCreationException {
        validateUser(user);
        String countryCode = extractCountryCode(user);
        String iban = generateIbanForCountry(countryCode);
        Account newAccount = buildNewAccount(iban);
        return accountRepository.save(newAccount);
    }

    @Override
    public GenericResponse<AccountOverview> getUserAccountOverview(String userName) {
        User user = userRepository.findByUsername(userName);
        List<AccountOverview> accountOverviewList = user.getAccounts().stream()
                .map(account -> AccountOverview.builder()
                        .accountNumber(Optional.ofNullable(account).map(Account::getIbanNumber).orElse(""))
                        .balance(Optional.ofNullable(account).map(Account::getBalance).orElse(BigDecimal.valueOf(0.0)))
                        .accountType(Optional.ofNullable(account).map(Account::getAccountType).map(AccountType::getName).orElse(""))
                        .currency(Optional.ofNullable(account).map(Account::getCurrency).map(Currency::getName).orElse(""))
                        .build())
                .collect(Collectors.toList());

        return GenericResponse.<AccountOverview>builder()
                .data(accountOverviewList)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new AccountCreationException("User must not be null.");
        }
    }

    private String extractCountryCode(User user) {
        return user.getAddresses().stream()
                .map(address -> address.getCountry().getCountryCode())
                .findFirst()
                .orElseThrow(() -> new AccountCreationException("User must have at least one address with a country."));
    }

    private String generateIbanForCountry(String countryCode) {
        return IBANGenerator.generateIBAN(countryCode);
    }

    private Account buildNewAccount(String iban) {
        Account newAccount = new Account();
        newAccount.setIbanNumber(iban);
        newAccount.setAccountType(appConfiguration.getAccountTypeMap().get(ACCOUNT_TYPE_SAVING));
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setStatus(appConfiguration.getStatusMasterMap().get(Status.ACTIVE.toString()));
        newAccount.setCurrency(appConfiguration.getCurrencyMap().get(CURRENCY_TYPE_EURO));
        return newAccount;
    }
}
