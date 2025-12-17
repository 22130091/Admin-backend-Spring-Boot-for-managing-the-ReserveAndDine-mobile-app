package com.admin.pos.Reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.Reservation.dto.request.ReservationRequest;
import com.admin.pos.Reservation.dto.response.ReservationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<ReservationResponse>>> getAll() {
    return ResponseEntity.ok(ApiResponse.ok(service.getAll(), "Fetched all reservations"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ReservationResponse>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(ApiResponse.ok(service.getById(id), "Fetched reservation details"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ReservationResponse>> create(@RequestBody ReservationRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.ok(service.create(request), "Reservation created successfully"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ReservationResponse>> update(@PathVariable Long id,
      @RequestBody ReservationRequest request) {
    return ResponseEntity.ok(ApiResponse.ok(service.update(id, request), "Reservation updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok(ApiResponse.ok(null, "Reservation deleted successfully"));
  }
}
