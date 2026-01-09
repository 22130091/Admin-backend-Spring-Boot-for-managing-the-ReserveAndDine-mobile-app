package com.admin.pos.admin_module_order.mapper;

import com.admin.pos.admin_module_order.dto.AllergenDTO;
import com.admin.pos.admin_module_order.entity.Allergen;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AllergenMapper {

    AllergenDTO toDTO(Allergen allergen);

    @Mapping(target = "dishes", ignore = true)
    Allergen toEntity(AllergenDTO allergenDTO);

    List<AllergenDTO> toDTOList(List<Allergen> allergens);

    Set<AllergenDTO> toDTOSet(Set<Allergen> allergens);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    void updateEntityFromDTO(AllergenDTO allergenDTO, @MappingTarget Allergen allergen);
}

