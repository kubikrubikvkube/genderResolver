package com.github.kubikrubikvkube.genderResolver.name;

import com.github.kubikrubikvkube.genderResolver.NameRequest;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * This validator is validating user input and checking if user-defined name is correct
 */
public class NameRequestValidator {
    private final static Pattern pattern = Pattern.compile("[\\p{IsCyrillic}]", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private final static Predicate<String> notCyrillicSymbolPredicate = pattern.asMatchPredicate().negate();

    /**
     * Validate nameRequest and return ErrorMessage if there is some errors in user input
     *
     * @param nameRequest nameRequest
     * @return optional of error message, or Optional.empty() if request is valid
     */
    public static Optional<ErrorMessage> validate(NameRequest nameRequest) {
        String name = nameRequest.getName();

        if (name == null || name.isEmpty()) {
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
