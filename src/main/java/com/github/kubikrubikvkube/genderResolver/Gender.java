package com.github.kubikrubikvkube.genderResolver;

public enum Gender {
    MALE,
    FEMALE,
    UNKNOWN;

    public static Gender fromString(String str) {
        if (str == null) {
            return Gender.UNKNOWN;
        }
        switch (str) {
            case ("М"):
                return Gender.MALE;
            case ("Ж"):
                return Gender.FEMALE;
        }
        return null;
    }
}
