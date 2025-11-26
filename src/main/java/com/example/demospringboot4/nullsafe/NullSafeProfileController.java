package com.example.demospringboot4.nullsafe;

import java.util.UUID;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * REST controller that showcases how the service-level null-safety constraints propagate all the
 * way to the HTTP layer while taking advantage of {@link ResponseEntity#of(java.util.Optional)}
 * for null-safe responses.
 */
@RestController
@RequestMapping("/api/null-safe/profiles")
@Validated
public class NullSafeProfileController {

    private final NullSafeProfileService service;

    public NullSafeProfileController(NullSafeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NullSafeProfileResponse> create(@Valid @RequestBody NullSafeProfileRequest request,
                                                          UriComponentsBuilder uriBuilder) {
        final var created = service.createProfile(request);
        final var location = uriBuilder.cloneBuilder()
                .pathSegment(created.id().toString())
                .build()
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NullSafeProfileResponse> findById(@PathVariable @NonNull @NotNull UUID id) {
        return ResponseEntity.of(service.findById(id));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<NullSafeProfileResponse> updateEmail(@PathVariable @NonNull @NotNull UUID id,
                                                               @Valid @RequestBody NullSafeEmailUpdateRequest payload) {
        return ResponseEntity.ok(service.updateEmail(id, payload.email()));
    }
}
