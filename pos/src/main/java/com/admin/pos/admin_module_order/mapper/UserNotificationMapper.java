package com.admin.pos.admin_module_order.mapper;

import com.admin.pos.admin_module_order.dto.UserNotificationDTO;
import com.admin.pos.admin_module_order.entity.UserNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NotificationMapper.class})
public interface UserNotificationMapper {

    @Mapping(source = "notification", target = "notification")
    UserNotificationDTO toDTO(UserNotification userNotification);

    UserNotification toEntity(UserNotificationDTO userNotificationDTO);

    List<UserNotificationDTO> toDTOList(List<UserNotification> userNotifications);
}
