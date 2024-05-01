package com.xyzbank.eob.service;
import com.xyzbank.eob.configuration.AppConfiguration;
import com.xyzbank.eob.dto.response.AccountOverview;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.exception.AccountCreationException;
import com.xyzbank.eob.model.*;
import com.xyzbank.eob.repository.AccountRepository;
import com.xyzbank.eob.repository.UserRepository;
import com.xyzbank.eob.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AppConfiguration appConfiguration;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        Mockito.reset(accountRepository, appConfiguration, userRepository);
    }
    @Test
    void testGetUserAccountOverview() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        Account account = new Account();
        account.setIbanNumber("TestIBAN");
        account.setBalance(BigDecimal.valueOf(1000));
        account.setAccountType(new AccountType(1L, "Savings"));
        account.setCurrency(new Currency(1L, "Euro"));
        user.setAccounts(Collections.singleton(account));

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        // Act
        GenericResponse<AccountOverview> response = accountService.getUserAccountOverview("testUser");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(1, response.getData().size());
        AccountOverview accountOverview = response.getData().get(0);
        assertEquals("TestIBAN", accountOverview.getAccountNumber());
        assertEquals("Savings", accountOverview.getAccountType());
        assertEquals(BigDecimal.valueOf(1000), accountOverview.getBalance());
        assertEquals("Euro", accountOverview.getCurrency());
    }
    @Test
    public void testCreateAccountForUser() {
        User user = new User();
        Address address = new Address();
        Country country = new Country();
        country.setCountryCode("NL");
        StatusMaster statusMaster = new StatusMaster();
        address.setStreetAddress("mock data");
        address.setCountry(country);
        address.setStatus(statusMaster);
        user.setAddresses(new HashSet<>(Collections.singletonList(address)));

        Account account = new Account();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account result = accountService.createAccountForUser(user);

        assertNotNull(result);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    public void testCreateAccountForUserThrowsException() {
        assertThrows(AccountCreationException.class,
                () -> accountService.createAccountForUser(null));
    }

    @Test
    public void testGetUserAccountOverview_() {
        String userName = "testUser";
        User user = new User();
        user.setAccounts(Collections.emptySet());
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        GenericResponse<AccountOverview> response = accountService.getUserAccountOverview(userName);
        assertNotNull(response);
        assertTrue(response.getData().isEmpty());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}