package com.github.kubikrubikvkube.genderResolver.name;

import com.github.kubikrubikvkube.genderResolver.NameRequest;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class NameRequestValidator {
    private final static Pattern pattern = Pattern.compile("[\\p{IsCyrillic}]", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private final static Predicate<String> notCyrillicSymbolPredicate = pattern.asMatchPredicate().negate();

    public static Optional<ErrorMessage> validate(NameRequest nameRequest) {
        String name = nameRequest.getName();

        if (name.isEmpty()) {
            return Optional.of(new ErrorMessage("name shouldn't be empty"));
        } else if (name.isBlank()) {
            return Optional.of(new ErrorMessage("name shouldn't be blank"));
        } else if (name.chars().anyMatch(Character::isDigit)) {
            return Optional.of(new ErrorMessage("name shouldn't contain any digits"));
        } else if (name.chars().mapToObj(c -> (char) c).map(String::valueOf).anyMatch(notCyrillicSymbolPredicate)) {
            return Optional.of(new ErrorMessage("name shouldn't contain non-cyrillic characters"));
        } else {
            return Optional.empty();
        }

    }
}
