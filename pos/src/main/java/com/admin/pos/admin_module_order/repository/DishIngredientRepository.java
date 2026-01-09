package com.admin.pos.admin_module_order.repository;

import com.admin.pos.admin_module_order.entity.DishIngredient;
import com.admin.pos.admin_module_order.entity.DishIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishIngredientRepository extends JpaRepository<DishIngredient, DishIngredientId> {
    List<DishIngredient> findByDishId(Integer dishId);
}