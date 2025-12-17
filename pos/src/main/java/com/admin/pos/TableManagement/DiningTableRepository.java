package com.admin.pos.TableManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {
  boolean existsByTableCode(String tableCode);

  Optional<DiningTable> findByTableCode(String tableCode);
}
