package com.example.demospringboot4.nullsafe;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

import org.jspecify.annotations.Nullable;
import org.springframework.util.function.SingletonSupplier;

/**
 * Immutable DTO showcasing a simple null-safe builder that fails fast when required
 * attributes are missing and allows optional values to stay nullable.
 */
public record NullSafeProfileResponse(UUID id,
                                      String displayName,
                                      @Nullable String email,
                                      URI detailUri) {

    public NullSafeProfileResponse {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(displayName, "displayName");
        Objects.requireNonNull(detailUri, "detailUri");
    }

    public NullSafeProfileResponse withEmail(@Nullable String email) {
        return new NullSafeProfileResponse(id, displayName, email, detailUri);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String displayName;
        @Nullable
        private String email;
        private URI detailUri = URI.create("about:blank");

        public Builder id(UUID id) {
            this.id = Objects.requireNonNull(id, "id");
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = Objects.requireNonNull(displayName, "displayName");
            return this;
        }

        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        public Builder detailUri(URI detailUri) {
            this.detailUri = Objects.requireNonNull(detailUri, "detailUri");
            return this;
        }

        public NullSafeProfileResponse build() {
            return new NullSafeProfileResponse(
                    Objects.requireNonNull(id, "id"),
                    Objects.requireNonNull(displayName, "displayName"),
                    email,
                    Objects.requireNonNull(detailUri, "detailUri"));
        }

        /**
         * Wraps the builder with a {@link SingletonSupplier} so the built instance is created once
         * and reused safely without leaking nulls.
         */
        public Supplier<NullSafeProfileResponse> buildSingleton() {
            final SingletonSupplier<NullSafeProfileResponse> singleton = SingletonSupplier.of(this::build);
            return singleton::get;
        }
    }
}
