package com.admin.pos.TableManagement;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.TableManagement.dto.request.DiningTableRequest;
import com.admin.pos.TableManagement.dto.response.DiningTableResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class DiningTableController {

  private final DiningTableService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<DiningTableResponse>>> getAll() {
    return ResponseEntity.ok(ApiResponse.ok(service.getAll(), "Fetched all tables successfully"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<DiningTableResponse>> getById(@PathVariable Long id) {
    return ResponseEntity.ok(ApiResponse.ok(service.getById(id), "Fetched table successfully"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<DiningTableResponse>> create(@RequestBody DiningTableRequest request) {
    return ResponseEntity.ok(ApiResponse.ok(service.create(request), "Table created successfully"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<DiningTableResponse>> update(@PathVariable Long id,
      @RequestBody DiningTableRequest request) {
    return ResponseEntity.ok(ApiResponse.ok(service.update(id, request), "Table updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok(ApiResponse.ok(null, "Table deleted successfully"));
  }

  @PutMapping("/{tableCode}/status")
  public ResponseEntity<ApiResponse<DiningTableResponse>> updateStatus(
      @PathVariable String tableCode,
      @RequestBody Map<String, String> req) {
    // Lấy giá trị "status" từ body
    String statusStr = req.get("status");
    if (statusStr == null) {
      throw new IllegalArgumentException("Missing 'status' field in request body");
    }

    // Convert String -> Enum
    TableStatus status;
    try {
      status = TableStatus.valueOf(statusStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid status value: " + statusStr);
    }

    // Update
    DiningTableResponse updated = service.updateStatus(tableCode, status);

    return ResponseEntity.ok(
        ApiResponse.ok(updated, "Status updated successfully"));
  }
}
