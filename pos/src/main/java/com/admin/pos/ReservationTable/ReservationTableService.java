package com.admin.pos.ReservationTable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.admin.pos.Exception.BadRequestException;
import com.admin.pos.Exception.ResourceNotFoundException;
import com.admin.pos.Reservation.Reservation;
import com.admin.pos.Reservation.ReservationRepository;
import com.admin.pos.ReservationTable.dto.ReservationTableMapper;
import com.admin.pos.ReservationTable.dto.request.ReservationTableRequest;
import com.admin.pos.ReservationTable.dto.response.ReservationTableResponse;
import com.admin.pos.TableManagement.DiningTable;
import com.admin.pos.TableManagement.DiningTableRepository;
import com.admin.pos.TimeSlot.TimeSlot;
import com.admin.pos.TimeSlot.TimeSlotRepository;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ReservationTableService {
  private final ReservationTableRepository repository;
  private final ReservationTableMapper mapper;

  private final ReservationRepository reservationRepository;
  private final DiningTableRepository tableRepository;
  private final TimeSlotRepository slotRepository;

  // Lấy danh sách tất cả các ReservationTable
  public List<ReservationTableResponse> getAll() {
    return repository.findAll().stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
  }

  // Lấy thông tin chi tiết của một ReservationTable theo ID
  public ReservationTableResponse getById(Long id) {
    ReservationTable entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ReservationTable not found with id: " + id));
    return mapper.toResponse(entity);
  }

  public ReservationTableResponse create(ReservationTableRequest request) {
    // 1. Lấy các entity liên quan
    Reservation reservation = reservationRepository.findById(request.getReservationId())
        .orElseThrow(
            () -> new ResourceNotFoundException("Reservation not found with id: " + request.getReservationId()));

    DiningTable table = tableRepository.findById(request.getTableId())
        .orElseThrow(() -> new ResourceNotFoundException("DiningTable not found with id: " + request.getTableId()));

    TimeSlot slot = slotRepository.findById(request.getSlotId())
        .orElseThrow(() -> new ResourceNotFoundException("TimeSlot not found with id: " + request.getSlotId()));

    // 2. Map từ DTO sang Entity
    ReservationTable entity = mapper.toEntity(request, reservation, table, slot);

    // 3. Kiểm tra startTime < endTime
    if (mapper.mapStartTime(entity).isAfter(mapper.mapEndTime(entity))) {
      throw new BadRequestException("Start time must be before end time");
    }

    // 4. Lưu và trả về Response
    return mapper.toResponse(repository.save(entity));
  }

  public ReservationTableResponse update(Long id, ReservationTableRequest request) {
    // 1. Lấy entity hiện tại
    ReservationTable entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ReservationTable not found with id: " + id));

    // 2. Lấy Reservation, DiningTable, TimeSlot nếu có request thay đổi
    Reservation reservation = request.getReservationId() != null
        ? reservationRepository.findById(request.getReservationId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Reservation not found with id: " + request.getReservationId()))
        : entity.getReservation();

    DiningTable table = request.getTableId() != null
        ? tableRepository.findById(request.getTableId())
            .orElseThrow(() -> new ResourceNotFoundException("DiningTable not found with id: " + request.getTableId()))
        : entity.getDiningTable();

    TimeSlot slot = request.getSlotId() != null
        ? slotRepository.findById(request.getSlotId())
            .orElseThrow(() -> new ResourceNotFoundException("TimeSlot not found with id: " + request.getSlotId()))
        : entity.getTimeSlot();

    LocalDate date = request.getDate() != null ? request.getDate() : entity.getDate();

    // 3. Kiểm tra bàn đã được đặt cho thời gian đó chưa
    boolean exists = repository.existsReservation(
        table.getTableId(),
        date,
        slot.getSlotId(),
        List.of(ReservationTableStatus.Reserved, ReservationTableStatus.Occupied));
    // Nếu slot hoặc bàn bị thay đổi thì mới check trùng
    if (exists && !(entity.getDiningTable().getTableId() == table.getTableId() &&
        entity.getTimeSlot().getSlotId() == slot.getSlotId() &&
        entity.getDate().equals(date))) {
      throw new BadRequestException("Dining table already reserved for this time slot");
    }

    // 4. Cập nhật entity
    entity.setReservation(reservation);
    entity.setDiningTable(table);
    entity.setTimeSlot(slot);
    entity.setDate(date);
    entity
        .setGuestsAtTable(request.getGuestsAtTable() != null ? request.getGuestsAtTable() : entity.getGuestsAtTable());
    entity.setReservationTableStatus(request.getReservationTableStatus() != null
        ? mapper.tableStatusToReservationStatus(request.getReservationTableStatus())
        : entity.getReservationTableStatus());

    // 5. Lưu và trả về response
    return mapper.toResponse(repository.save(entity));
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("ReservationTable not found with id: " + id);
    }
    repository.deleteById(id);
  }
}
