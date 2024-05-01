package com.xyzbank.eob.util;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword() {
        Integer length = 12;
        return IntStream.range(0, length) // Stream of the required length
                .map(i -> random.nextInt(PASSWORD_ALLOW_BASE.length())) // Map each element to a random index of the base chars
                .mapToObj(PASSWORD_ALLOW_BASE::charAt) // Map each random index to corresponding character
                .map(Object::toString) // Convert each character to string
                .collect(Collectors.joining()); // Join them together to form the password
    }
}

