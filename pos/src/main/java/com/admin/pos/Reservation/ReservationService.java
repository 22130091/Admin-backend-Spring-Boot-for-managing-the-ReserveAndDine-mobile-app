package com.admin.pos.Reservation;

import java.util.List;
import org.springframework.stereotype.Service;

import com.admin.pos.Exception.BadRequestException;
import com.admin.pos.Exception.ResourceNotFoundException;
import com.admin.pos.Reservation.dto.ReservationMapper;
import com.admin.pos.Reservation.dto.request.ReservationRequest;
import com.admin.pos.Reservation.dto.response.ReservationResponse;
import com.admin.pos.User.User;
import com.admin.pos.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository repository;
  private final ReservationMapper mapper;
  private final UserRepository userRepository;

  public List<ReservationResponse> getAll() {
    return mapper.toResponseList(repository.findAll());
  }

  public ReservationResponse getById(Long id) {
    return repository.findById(id)
        .map(mapper::toResponse)
        .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
  }

  public ReservationResponse update(Long id, ReservationRequest request) {
    Reservation existing = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reservation not found"));
    existing.setContactName(request.getContactName());
    existing.setContactPhone(request.getContactPhone());
    existing.setContactEmail(request.getContactEmail());
    existing.setTotalGuests(request.getTotalGuests());
    // Chỉ set nếu request có giá trị hợp lệ
    // 🔹 Sửa lỗi gán enum
    if (request.getReservationStatus() != null) {
      existing.setReservationStatus(request.getReservationStatus());
    }

    return mapper.toResponse(repository.save(existing));
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public ReservationResponse create(ReservationRequest dto) {
    if (repository.existsByBookingCode(dto.getBookingCode())) {
      throw new BadRequestException("Booking code already exists: " + dto.getBookingCode());
    }

    Reservation reservation = mapper.toEntity(dto);

    // 🔧 FIX: Load user từ DB trước khi gán
    if (dto.getUserId() != null) {
      User user = userRepository.findById(dto.getUserId())
          .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
      reservation.setUser(user);
    } else {
      reservation.setUser(null); // cho phép null nếu không cần login
    }

    // 🔹 đảm bảo trạng thái ban đầu
    reservation.setReservationStatus(ReservationStatus.CONFIRMED);

    return mapper.toResponse(repository.save(reservation));
  }

}
