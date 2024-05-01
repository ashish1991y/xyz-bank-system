package com.xyzbank.eob.util;

import java.util.Random;

public class IBANGenerator {
    private static final String COUNTRY_CODE = "NL";
    private static final int BBAN_LENGTH = 18; // Length of the BBAN for our example country

    public static String generateIBAN(String countryCode) {
        // Generate random BBAN
        String bban = generateRandomNumericString(BBAN_LENGTH);
        // Placeholder check digits (00), will be replaced later
        String tempIban = COUNTRY_CODE + "00" + bban;

        // Calculate check digits
        String checkDigits = calculateCheckDigits(tempIban);

        // Construct the final IBAN
        return COUNTRY_CODE + checkDigits + bban;
    }

    private static String generateRandomNumericString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Append a random digit
        }
        return sb.toString();
    }

    private static String calculateCheckDigits(String iban) {
        // Replace each letter with two digits, where A = 10, B = 11, ..., Z = 35
        String numericIban = iban.substring(4) + iban.substring(0, 4); // Rearrange for check digit calculation
        StringBuilder numericAccountNumber = new StringBuilder();
        for (char character : numericIban.toCharArray()) {
            if (Character.isLetter(character)) {
                numericAccountNumber.append(Character.getNumericValue(character));
            } else {
                numericAccountNumber.append(character);
            }
        }

        // Calculate the modulo 97 of the big integer representation
        java.math.BigInteger ibanNumber = new java.math.BigInteger(numericAccountNumber.toString());
        int checkDigit = 98 - ibanNumber.mod(java.math.BigInteger.valueOf(97)).intValue();

        // Format check digits (ensure two digits, prepend with 0 if necessary)
        return String.format("%02d", checkDigit);
    }
}
