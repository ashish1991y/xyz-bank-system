package com.xyzbank.eob.configuration;

import com.xyzbank.eob.model.AccountType;
import com.xyzbank.eob.model.Currency;
import com.xyzbank.eob.model.StatusMaster;
import com.xyzbank.eob.repository.AccountTypeRepository;
import com.xyzbank.eob.repository.CurrencyRepository;
import com.xyzbank.eob.repository.StatusMasterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
    private final AccountTypeRepository accountTypeRepository;
    private final CurrencyRepository currencyRepository;
    private final StatusMasterRepository statusMasterRepository;
    private Map<String, AccountType> accountTypeMap;
    private Map<String, Currency> currencyMap;
    private Map<String, StatusMaster> statusMasterMap;

    @PostConstruct
    public void init() {
        accountTypeMap = accountTypeRepository.findAll()
                .stream()
                .collect(Collectors.toMap(AccountType::getName, Function.identity()));

        currencyMap = currencyRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Currency::getName, obj -> obj));

        statusMasterMap = statusMasterRepository.findAll()
                .stream()
                .collect(Collectors.toMap(StatusMaster::getName, obj -> obj));
    }

    public Map<String, AccountType> getAccountTypeMap() {
        return accountTypeMap;
    }

    public Map<String, Currency> getCurrencyMap() {
        return currencyMap;
    }

    public Map<String, StatusMaster> getStatusMasterMap() {
        return statusMasterMap;
    }
}
