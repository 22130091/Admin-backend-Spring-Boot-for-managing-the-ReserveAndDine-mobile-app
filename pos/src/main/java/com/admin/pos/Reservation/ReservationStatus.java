package com.admin.pos.Reservation;

public enum ReservationStatus {
  PENDING, // Đang xử lý
  CONFIRMED, // Đã xác nhận
  CHECKED_IN, // đã vào bàn
  COMPLETED, // đã rời bàn
  CANCELLED // đã hủy
}
