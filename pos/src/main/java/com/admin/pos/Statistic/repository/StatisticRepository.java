package com.admin.pos.Statistic.repository;

import com.admin.pos.admin_module_order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT dt.table_code, COUNT(o.order_id), SUM(o.total_amount) " +
            "FROM orders o " +
            "JOIN reservation_tables rt ON o.reservation_table_id = rt.reservation_table_id " +
            "JOIN dining_tables dt ON rt.table_id = dt.table_id " +
            "WHERE o.payment_status = 'paid' " +
            "GROUP BY dt.table_code", nativeQuery = true)
    List<Object[]> getRawTableStatistics();

    @Query(value = "SELECT YEAR(order_time) as year, MONTH(order_time) as month, SUM(total_amount) as revenue " +
            "FROM orders " +
            "WHERE payment_status = 'paid' " +
            "  AND order_time >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH) " +
            "GROUP BY YEAR(order_time), MONTH(order_time) " +
            "ORDER BY year ASC, month ASC", nativeQuery = true)
    List<Object[]> getMonthlyRevenueRaw();
}