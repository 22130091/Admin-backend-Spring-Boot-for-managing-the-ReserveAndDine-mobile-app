package com.admin.pos.ReservationTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationTableRepository extends JpaRepository<ReservationTable, Long> {
  List<ReservationTable> findByDiningTable_TableCode(String tableCode);

  @Query("""
          SELECT CASE WHEN COUNT(rt) > 0 THEN TRUE ELSE FALSE END
          FROM ReservationTable rt
          WHERE rt.diningTable.tableId = :tableId
            AND rt.date = :date
            AND rt.timeSlot.slotId = :slotId
            AND rt.reservationTableStatus IN :statuses
      """)
  boolean existsReservation(
      @Param("tableId") Long tableId,
      @Param("date") LocalDate date,
      @Param("slotId") Long slotId,
      @Param("statuses") List<ReservationTableStatus> statuses);
}