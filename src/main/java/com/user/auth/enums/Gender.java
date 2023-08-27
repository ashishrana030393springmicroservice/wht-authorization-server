package com.user.auth.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE(0), FEMALE(1), OTHER(3);
    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static boolean isValid(String value){
        return Arrays.stream(Gender.values()).anyMatch(gender-> gender.name().equals(value));
    }

    public static Gender findByCode(Integer code){
        Optional<Gender> first = Arrays.stream(Gender.values()).filter(gender -> gender.getValue() == code).findFirst();
        return first.map(gender-> gender).orElse(Gender.OTHER);
    }
}
