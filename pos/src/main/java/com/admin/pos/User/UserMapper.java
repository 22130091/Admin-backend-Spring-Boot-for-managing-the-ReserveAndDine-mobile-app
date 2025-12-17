package com.admin.pos.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDTO toDTO(User user);

  User toEntity(UserDTO dto);
}
