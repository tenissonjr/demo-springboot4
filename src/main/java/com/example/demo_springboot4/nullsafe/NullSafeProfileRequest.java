package com.example.demo_springboot4.nullsafe;

import java.util.Objects;

import lombok.NonNull;
import org.jspecify.annotations.Nullable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO leveraging Spring's nullability annotations so that both static analysis
 * and method validation agree on which fields may be null.
 */
public record NullSafeProfileRequest(
        @NonNull @NotBlank(message = "displayName is required") String displayName,
        @Nullable @Email(message = "email must be valid when provided") String email) {

    public NullSafeProfileRequest {
        Objects.requireNonNull(displayName, "displayName");
        final var normalizedDisplayName = displayName.trim();
        if (normalizedDisplayName.isEmpty()) {
            throw new IllegalArgumentException("displayName must contain non-blank text");
        }
        displayName = normalizedDisplayName;
        if (email != null && email.isBlank()) {
            email = null; // Normalize blank emails to null so downstream code stays null-safe.
        }
    }
}
