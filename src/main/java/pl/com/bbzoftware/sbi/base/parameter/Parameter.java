package pl.com.bbzoftware.sbi.base.parameter;

import java.util.Arrays;

public enum Parameter {
    CITY("city");

    private String name;

    Parameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isValid(String value) {
        return Arrays.stream(values())
                .anyMatch(v -> v.getName().equals(value));
    }
}

