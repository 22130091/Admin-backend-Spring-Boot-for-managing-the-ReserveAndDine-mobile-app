package com.admin.pos.admin_module_order.controller;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.admin_module_order.dto.DishDTO;
import com.admin.pos.admin_module_order.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/admin/dishes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DishDTO>>> getAllDishes() {
        List<DishDTO> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes retrieved successfully", dishes));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getActiveDishes() {
        List<DishDTO> dishes = dishService.getActiveDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Active dishes retrieved successfully", dishes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DishDTO>> getDishById(@PathVariable Integer id) {
        DishDTO dish = dishService.getDishById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish retrieved successfully", dish));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DishDTO>>> searchDishes(@RequestParam String name) {
        List<DishDTO> dishes = dishService.searchDishesByName(name);
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results retrieved successfully", dishes));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByCategory(@PathVariable Integer categoryId) {
        List<DishDTO> dishes = dishService.getDishesByCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes by category retrieved successfully", dishes));
    }

    @GetMapping("/price-range")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<DishDTO> dishes = dishService.getDishesByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes by price range retrieved successfully", dishes));
    }

    @GetMapping("/vegetarian")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getVegetarianDishes() {
        List<DishDTO> dishes = dishService.getVegetarianDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Vegetarian dishes retrieved successfully", dishes));
    }

    @GetMapping("/vegan")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getVeganDishes() {
        List<DishDTO> dishes = dishService.getVeganDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Vegan dishes retrieved successfully", dishes));
    }

    @GetMapping("/spicy")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getSpicyDishes() {
        List<DishDTO> dishes = dishService.getSpicyDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Spicy dishes retrieved successfully", dishes));
    }

    @GetMapping("/allergen/{allergenId}")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByAllergen(@PathVariable Integer allergenId) {
        List<DishDTO> dishes = dishService.getDishesByAllergen(allergenId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes with allergen retrieved successfully", dishes));
    }

    @GetMapping("/ingredient/{ingredientId}")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByIngredient(@PathVariable Integer ingredientId) {
        List<DishDTO> dishes = dishService.getDishesByIngredient(ingredientId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes with ingredient retrieved successfully", dishes));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DishDTO>> createDish(@Valid @RequestBody DishDTO dishDTO) {
        DishDTO createdDish = dishService.createDish(dishDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Dish created successfully", createdDish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DishDTO>> updateDish(
            @PathVariable Integer id,
            @Valid @RequestBody DishDTO dishDTO) {
        DishDTO updatedDish = dishService.updateDish(id, dishDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish updated successfully", updatedDish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(@PathVariable Integer id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish deleted successfully", null));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<Void>> activateDish(@PathVariable Integer id) {
        dishService.activateDish(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish activated successfully", null));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateDish(@PathVariable Integer id) {
        dishService.deactivateDish(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish deactivated successfully", null));
    }
}

