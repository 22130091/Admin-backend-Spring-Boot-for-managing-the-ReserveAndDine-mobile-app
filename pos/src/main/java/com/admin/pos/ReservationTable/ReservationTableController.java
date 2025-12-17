package com.admin.pos.ReservationTable;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.ReservationTable.dto.request.ReservationTableRequest;
import com.admin.pos.ReservationTable.dto.response.ReservationTableResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservation-tables")
@RequiredArgsConstructor
public class ReservationTableController {

  private final ReservationTableService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<ReservationTableResponse>>> getAll() {
    return ResponseEntity.ok(ApiResponse.ok(service.getAll(), "Fetched all reservation tables"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ReservationTableResponse>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(ApiResponse.ok(service.getById(id), "Fetched reservation table details"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ReservationTableResponse>> create(@RequestBody ReservationTableRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.ok(service.create(request), "Reservation table created successfully"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ReservationTableResponse>> update(@PathVariable Long id,
      @RequestBody ReservationTableRequest request) {
    return ResponseEntity.ok(ApiResponse.ok(service.update(id, request), "Reservation table updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok(ApiResponse.ok(null, "Reservation table deleted successfully"));
  }

}
