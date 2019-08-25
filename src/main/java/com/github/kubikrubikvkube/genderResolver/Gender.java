package com.github.kubikrubikvkube.genderResolver;

/**
 * Available genders
 */
public enum Gender {
    MALE,
    FEMALE,
    UNKNOWN;

    /**
     * Resolve gender from string
     *
     * @param str gender string, e.x. "М"
     * @return Gender enum object
     */
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
