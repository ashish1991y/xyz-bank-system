package com.xyzbank.eob.controller;

import com.xyzbank.eob.dto.response.AccountOverview;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.service.AccountService;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testUserOverviewSuccess() throws Exception {
        String userName = "testUser";
        AccountOverview overview = new AccountOverview("ID123", "Test User", new BigDecimal("1000"), "Active");
        GenericResponse<AccountOverview> response = GenericResponse.<AccountOverview>builder()
                .data(Arrays.asList(overview))
                .statusCode(HttpStatus.SC_OK)
                .build();

        when(accountService.getUserAccountOverview(userName)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/overview")
                        .header("userName", userName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].accountNumber").value("ID123"))
                .andExpect(jsonPath("$.data[0].accountType").value("Test User"))
                .andExpect(jsonPath("$.data[0].balance").value(1000))
                .andExpect(jsonPath("$.data[0].currency").value("Active"))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.SC_OK));
    }
    @Test
    void testLoginSuccess() throws Exception {
        String userName = "testUser";

        mockMvc.perform(MockMvcRequestBuilders.get("/account/login")
                        .header("userName", userName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].message").value("Hello,testUser Successfully logged in"))
                .andExpect(jsonPath("$.statusCode").value(200)); // Assuming the status code set in the controller is HTTP 200 for a successful login
    }
}