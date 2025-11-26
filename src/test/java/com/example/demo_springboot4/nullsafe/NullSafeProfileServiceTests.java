package com.example.demo_springboot4.nullsafe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
class NullSafeProfileServiceTests {

    @Autowired
    private NullSafeProfileService service;

    @Test
    void rejectsNullIdentifierAtRuntime() {
        assertThatThrownBy(() -> service.updateEmail(unsafeNullUuid(), "valid@example.com"))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("updateEmail.id");
    }

    @Test
    void allowsClearingEmailSafely() {
        final var created = service.createProfile(new NullSafeProfileRequest("Alice", "alice@example.com"));
        final var updated = service.updateEmail(created.id(), (String) null);

        assertThat(updated.id()).isEqualTo(created.id());
        assertThat(updated.email()).isNull();
    }
    
    private UUID unsafeNullUuid() {
        return (UUID) null;
    }
}
