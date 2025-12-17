package com.admin.pos.Reservation.dto;

import java.util.List;

import org.mapstruct.*;

import com.admin.pos.Reservation.Reservation;
import com.admin.pos.Reservation.dto.request.ReservationRequest;
import com.admin.pos.Reservation.dto.response.ReservationResponse;
import com.admin.pos.ReservationTable.dto.ReservationTableMapper;
import com.admin.pos.User.User;

@Mapper(componentModel = "spring", uses = { ReservationTableMapper.class })
public interface ReservationMapper {

  @Mapping(source = "user.userId", target = "userId")
  ReservationResponse toResponse(Reservation entity);

  @Mapping(target = "user", expression = "java(dto.getUserId() != null ? new User(dto.getUserId()) : null)")
  Reservation toEntity(ReservationRequest dto);

  List<ReservationResponse> toResponseList(List<Reservation> entities);

  default User mapUserIdToUser(Long userId) {
    if (userId == null)
      return null;
    return new User(userId);
  }
}
