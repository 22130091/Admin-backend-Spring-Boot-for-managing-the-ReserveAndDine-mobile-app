package com.admin.pos.admin_module_order.controller;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.admin_module_order.dto.IngredientDTO;
import com.admin.pos.admin_module_order.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IngredientDTO>>> getAllIngredients() {
        List<IngredientDTO> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredients retrieved successfully", ingredients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientDTO>> getIngredientById(@PathVariable Integer id) {
        IngredientDTO ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredient retrieved successfully", ingredient));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IngredientDTO>> createIngredient(@Valid @RequestBody IngredientDTO ingredientDTO) {
        IngredientDTO createdIngredient = ingredientService.createIngredient(ingredientDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Ingredient created successfully", createdIngredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientDTO>> updateIngredient(
            @PathVariable Integer id,
            @Valid @RequestBody IngredientDTO ingredientDTO) {
        IngredientDTO updatedIngredient = ingredientService.updateIngredient(id, ingredientDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredient updated successfully", updatedIngredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIngredient(@PathVariable Integer id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredient deleted successfully", null));
    }
}

