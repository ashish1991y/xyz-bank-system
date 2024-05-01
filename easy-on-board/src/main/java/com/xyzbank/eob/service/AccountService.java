package com.xyzbank.eob.service;

import com.xyzbank.eob.dto.response.AccountOverview;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.exception.AccountCreationException;
import com.xyzbank.eob.model.Account;
import com.xyzbank.eob.model.User;

public interface AccountService {
    Account createAccountForUser(User user) throws AccountCreationException;

    GenericResponse<AccountOverview> getUserAccountOverview(String userName);
}
