package app.user.landlord.controller;

import app.user.landlord.dto.LandlordRequestDto;
import app.user.landlord.dto.LandlordResponseDto;
import app.user.landlord.service.LandlordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/landlords")
@RequiredArgsConstructor
public class LandlordController {

    private final LandlordService landlordService;

    @PostMapping
    public ResponseEntity<LandlordResponseDto> create(@Valid @RequestBody LandlordRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(landlordService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandlordResponseDto> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(landlordService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<LandlordResponseDto>> getAll() {
        return ResponseEntity.ok(landlordService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandlordResponseDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody LandlordRequestDto request) {
        return ResponseEntity.ok(landlordService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        landlordService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<LandlordResponseDto> activate(@PathVariable UUID id) {
        return ResponseEntity.ok(landlordService.activate(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<LandlordResponseDto> deactivate(@PathVariable UUID id) {
        return ResponseEntity.ok(landlordService.deactivate(id));
    }
}