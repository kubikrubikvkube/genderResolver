package com.github.kubikrubikvkube.genderResolver.name;

import lombok.Value;

/**
 * This ErrorMessage will be returned to user if NameRequest is invalid
 */
@Value
public class ErrorMessage {
    /**
     * Error message
     */
    private final String errorMessage;
}
