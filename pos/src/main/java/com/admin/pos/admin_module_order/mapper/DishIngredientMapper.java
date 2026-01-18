package com.admin.pos.admin_module_order.mapper;

import com.admin.pos.admin_module_order.dto.DishIngredientDTO;
import com.admin.pos.admin_module_order.entity.DishIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DishIngredientMapper {

    @Mapping(source = "ingredient.id", target = "ingredientId")
    @Mapping(source = "ingredient.name", target = "ingredientName")
    DishIngredientDTO toDTO(DishIngredient dishIngredient);

    Set<DishIngredientDTO> toDTOSet(Set<DishIngredient> dishIngredients);
}
