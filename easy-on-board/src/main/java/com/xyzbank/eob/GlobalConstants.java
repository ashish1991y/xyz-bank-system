package com.xyzbank.eob;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GlobalConstants {

    //Supported format DD/MM/YYYY
    public static final String DOB_REGEX = "^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/((19|20)\\d{2})$";

    public static final String ACCOUNT_TYPE_SAVING = "SAVING";
    public static final String CURRENCY_TYPE_EURO = "EURO";

    public static final String ROLE_USER = "USER";

    public static final String ROLE_ADMIN = "ADMIN";
}
