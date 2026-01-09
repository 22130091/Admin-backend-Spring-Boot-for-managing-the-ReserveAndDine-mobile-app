package com.admin.pos.admin_module_order.service;

import com.admin.pos.admin_module_order.dto.OrderDTO;
import com.admin.pos.admin_module_order.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Integer id);
    List<OrderDTO> getOrdersByCustomerId(Integer customerId);
    List<OrderDTO> getOrdersByReservationId(Integer reservationId);
    List<OrderDTO> getOrdersByStatus(Order.OrderStatus status);
    List<OrderDTO> getOrdersByPaymentStatus(Order.PaymentStatus paymentStatus);
    List<OrderDTO> getOrdersByDateRange(LocalDateTime start, LocalDateTime end);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Integer id, OrderDTO orderDTO);
    OrderDTO updateOrderStatus(Integer id, Order.OrderStatus status);
    OrderDTO updatePaymentStatus(Integer id, Order.PaymentStatus paymentStatus);
    void deleteOrder(Integer id);
}