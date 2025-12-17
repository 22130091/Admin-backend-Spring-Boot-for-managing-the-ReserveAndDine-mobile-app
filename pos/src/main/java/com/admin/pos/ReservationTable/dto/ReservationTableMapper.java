package com.admin.pos.ReservationTable.dto;

import java.time.LocalDateTime;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.admin.pos.Reservation.Reservation;
import com.admin.pos.ReservationTable.ReservationTable;
import com.admin.pos.ReservationTable.ReservationTableStatus;
import com.admin.pos.ReservationTable.dto.request.ReservationTableRequest;
import com.admin.pos.ReservationTable.dto.response.ReservationTableResponse;
import com.admin.pos.TableManagement.DiningTable;
import com.admin.pos.TableManagement.TableStatus;
import com.admin.pos.TimeSlot.TimeSlot;

@Mapper(componentModel = "spring")
public interface ReservationTableMapper {

  // ReservationTableMapper INSTANCE = Mappers.getMapper(ReservationTableMapper.class);

  // ENTITY → RESPONSE
  @Mapping(source = "reservation.reservationId", target = "reservationId")
  @Mapping(source = "diningTable.tableId", target = "tableId")
  @Mapping(target = "startTime", source = ".", qualifiedByName = "mapStartTime")
  @Mapping(target = "endTime", source = ".", qualifiedByName = "mapEndTime")
  @Mapping(source = "reservationTableStatus", target = "reservationTableStatus", qualifiedByName = "reservationStatusToTableStatus")
  ReservationTableResponse toResponse(ReservationTable entity);

  // REQUEST → ENTITY
  default ReservationTable toEntity(
      ReservationTableRequest request,
      Reservation reservation,
      DiningTable table,
      TimeSlot slot) {
    if (request == null)
      return null;

    return ReservationTable.builder()
        .reservation(reservation)
        .diningTable(table)
        .timeSlot(slot)
        .date(request.getDate())
        .guestsAtTable(request.getGuestsAtTable())
        .reservationTableStatus(tableStatusToReservationStatus(request.getReservationTableStatus()))
        .createdAt(LocalDateTime.now())
        .build();
  }

  // ---------------- Helper Methods ----------------

  @Named("mapStartTime")
  default LocalDateTime mapStartTime(ReservationTable entity) {
    if (entity == null || entity.getTimeSlot() == null || entity.getDate() == null)
      return null;
    return LocalDateTime.of(entity.getDate(), entity.getTimeSlot().getStartTime());
  }

  @Named("mapEndTime")
  default LocalDateTime mapEndTime(ReservationTable entity) {
    if (entity == null || entity.getTimeSlot() == null || entity.getDate() == null)
      return null;
    return LocalDateTime.of(entity.getDate(), entity.getTimeSlot().getEndTime());
  }

  // Enum mapping: ENTITY → DTO
  @Named("reservationStatusToTableStatus")
  default TableStatus reservationStatusToTableStatus(ReservationTableStatus status) {
    if (status == null)
      return null;
    switch (status) {
      case Reserved:
      case Finished:
      case Cancelled:
        return TableStatus.EMPTY;
      case Occupied:
        return TableStatus.OCCUPIED;
      default:
        throw new IllegalArgumentException("Unknown ReservationTableStatus: " + status);
    }
  }

  // Enum mapping: DTO → ENTITY
  default ReservationTableStatus tableStatusToReservationStatus(TableStatus status) {
    if (status == null)
      return null;
    switch (status) {
      case EMPTY:
        return ReservationTableStatus.Reserved;
      case OCCUPIED:
        return ReservationTableStatus.Occupied;
      default:
        throw new IllegalArgumentException("Unknown TableStatus: " + status);
    }
  }

}
