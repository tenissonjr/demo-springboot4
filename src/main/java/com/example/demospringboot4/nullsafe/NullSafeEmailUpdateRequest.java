package com.example.demospringboot4.nullsafe;

import org.jspecify.annotations.Nullable;

import jakarta.validation.constraints.Email;

/**
 * Payload for updating a profile's email, allowing clients to explicitly clear the value
 * by submitting {@code null}.
 */
public record NullSafeEmailUpdateRequest(@Nullable @Email String email) {
}
