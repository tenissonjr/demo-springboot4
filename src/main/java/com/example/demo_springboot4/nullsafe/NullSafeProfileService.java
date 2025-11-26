package com.example.demo_springboot4.nullsafe;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Application service showing how Spring Boot 4 method validation cooperates with
 * JSpecify nullability metadata to guard against accidental {@code null} propagation.
 */
@Service
@Validated
public class NullSafeProfileService {

    private final Map<UUID, NullSafeProfileResponse> profiles = new ConcurrentHashMap<>();
    private final Supplier<NullSafeProfileResponse> guestProfile = NullSafeProfileResponse.builder()
            .id(UUID.randomUUID())
            .displayName("Guest")
            .detailUri(URI.create("about:blank"))
            .buildSingleton();

    public NullSafeProfileResponse createProfile(@Valid @NonNull NullSafeProfileRequest request) {
        final UUID id = UUID.randomUUID();
        final var profile = NullSafeProfileResponse.builder()
                .id(id)
                .displayName(request.displayName())
                .email(sanitizeEmail(request.email()))
                .detailUri(URI.create("/api/null-safe/profiles/" + id))
                .build();
        profiles.put(profile.id(), profile);
        return profile;
    }

    public Optional<NullSafeProfileResponse> findById(@NonNull @NotNull UUID id) {
        return Optional.ofNullable(profiles.get(id));
    }

    public NullSafeProfileResponse updateEmail(@NonNull @NotNull UUID id,
                                               @Nullable String email) {
        final String normalizedEmail = sanitizeEmail(email);
        final NullSafeProfileResponse updated = profiles.computeIfPresent(id,
                (key, existing) -> existing.withEmail(normalizedEmail));
        if (updated == null) {
            return guestProfile.get();
        }
        profiles.put(id, updated);
        return updated;
    }

    private @Nullable String sanitizeEmail(@Nullable String email) {
        if (!StringUtils.hasText(email)) {
            return null;
        }
        return email.trim();
    }
}
