package com.github.kubikrubikvkube.genderResolver.name;

import com.github.kubikrubikvkube.genderResolver.NameRequest;

import java.util.Optional;

public class NameRequestValidator {
    public static Optional<ErrorMessage> validate(NameRequest nameRequest) {
        if (nameRequest.getName().isEmpty()) {
            return Optional.of(new ErrorMessage("'name' shouldn't be empty"));
        } else if (nameRequest.getName().isBlank()) {
            return Optional.of(new ErrorMessage("'name' shouldn't be blank"));
        } else {
            return Optional.empty();
        }

    }
}
