package com.admin.pos.admin_module_order.mapper;

import com.admin.pos.admin_module_order.dto.NotificationDTO;
import com.admin.pos.admin_module_order.entity.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDTO(Notification notification);

    Notification toEntity(NotificationDTO notificationDTO);

    List<NotificationDTO> toDTOList(List<Notification> notifications);
}
