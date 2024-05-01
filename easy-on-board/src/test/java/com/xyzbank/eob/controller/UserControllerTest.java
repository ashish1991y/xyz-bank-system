package com.xyzbank.eob.controller;


import com.xyzbank.eob.dto.request.UserRequest;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.dto.response.UserRegisterResponse;
import com.xyzbank.eob.service.UserService;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testRegisterUser() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse("mock", "mock");
        GenericResponse<UserRegisterResponse>  response= GenericResponse.<UserRegisterResponse>builder()
                .data(Arrays.asList(userRegisterResponse))
                .statusCode(HttpStatus.SC_OK)
                .build();
        Mockito.when(userService.register(any(UserRequest.class), any(MultipartFile.class)))
                .thenReturn(response);


        String userData = "{\"name\": \"ashish\", \"address\": \"blabla\", \"dob\": \"26/10/2001\", \"username\": \"ashish115\", \"country\": \"Netherlands\", \"documentType\": \"DRIVING_LICENCE\"}";

        // Act
        ResponseEntity<GenericResponse<UserRegisterResponse>> responseEntity = userController.registerUser(file, userData);

        // Assert
        assertEquals(HttpStatus.SC_OK, responseEntity.getStatusCode().value());
        assertEquals("mock", responseEntity.getBody().getData().get(0).getUserName());
        assertEquals("mock", responseEntity.getBody().getData().get(0).getPassword());
    }
}