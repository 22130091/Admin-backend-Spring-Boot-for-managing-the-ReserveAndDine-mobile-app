package com.admin.pos.admin_module_order.repository;

import com.admin.pos.admin_module_order.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String name);
    List<Category> findByActive(Boolean active);
}