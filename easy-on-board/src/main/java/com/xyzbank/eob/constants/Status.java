package com.xyzbank.eob.constants;

public enum Status {
    ACTIVE(1),
    INACTIVE(2),
    DELETE(3);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getNameByValue(int value) {
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                return status.name();
            }
        }
        return "";
    }
}
