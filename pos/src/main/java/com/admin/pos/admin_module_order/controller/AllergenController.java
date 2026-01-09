package com.admin.pos.admin_module_order.controller;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.admin_module_order.dto.AllergenDTO;
import com.admin.pos.admin_module_order.service.AllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/allergens")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AllergenController {

    private final AllergenService allergenService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AllergenDTO>>> getAllAllergens() {
        List<AllergenDTO> allergens = allergenService.getAllAllergens();
        return ResponseEntity.ok(new ApiResponse<>(true, "Allergens retrieved successfully", allergens));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AllergenDTO>> getAllergenById(@PathVariable Integer id) {
        AllergenDTO allergen = allergenService.getAllergenById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Allergen retrieved successfully", allergen));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AllergenDTO>> createAllergen(@Valid @RequestBody AllergenDTO allergenDTO) {
        AllergenDTO createdAllergen = allergenService.createAllergen(allergenDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Allergen created successfully", createdAllergen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AllergenDTO>> updateAllergen(
            @PathVariable Integer id,
            @Valid @RequestBody AllergenDTO allergenDTO) {
        AllergenDTO updatedAllergen = allergenService.updateAllergen(id, allergenDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Allergen updated successfully", updatedAllergen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAllergen(@PathVariable Integer id) {
        allergenService.deleteAllergen(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Allergen deleted successfully", null));
    }
}

