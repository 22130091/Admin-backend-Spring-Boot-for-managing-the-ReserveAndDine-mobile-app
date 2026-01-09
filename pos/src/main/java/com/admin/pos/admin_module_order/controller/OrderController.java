package com.admin.pos.admin_module_order.controller;

import com.admin.pos.Exception.BadRequestException;
import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.admin_module_order.dto.OrderDTO;
import com.admin.pos.admin_module_order.entity.Order;
import com.admin.pos.admin_module_order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new ApiResponse<>(true, "Orders retrieved successfully", orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderById(@PathVariable Integer id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order retrieved successfully", order));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByCustomer(@PathVariable Integer customerId) {
        List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Customer orders retrieved successfully", orders));
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByReservation(@PathVariable Integer reservationId) {
        List<OrderDTO> orders = orderService.getOrdersByReservationId(reservationId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Reservation orders retrieved successfully", orders));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(new ApiResponse<>(true, "Orders by status retrieved successfully", orders));
    }

    @GetMapping("/payment-status/{paymentStatus}")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByPaymentStatus(@PathVariable Order.PaymentStatus paymentStatus) {
        List<OrderDTO> orders = orderService.getOrdersByPaymentStatus(paymentStatus);
        return ResponseEntity.ok(new ApiResponse<>(true, "Orders by payment status retrieved successfully", orders));
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<OrderDTO> orders = orderService.getOrdersByDateRange(start, end);
        return ResponseEntity.ok(new ApiResponse<>(true, "Orders by date range retrieved successfully", orders));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrderStatus(
            @PathVariable Integer id,
            @RequestParam Order.OrderStatus status) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order status updated successfully", updatedOrder));
    }

    @PatchMapping("/{id}/payment-status")
    public ResponseEntity<ApiResponse<OrderDTO>> updatePaymentStatus(
            @PathVariable Integer id,
            @RequestParam Order.PaymentStatus paymentStatus) {
        
        OrderDTO currentOrder = orderService.getOrderById(id);
        
        if (currentOrder.getPaymentStatus() == Order.PaymentStatus.paid
                && paymentStatus == Order.PaymentStatus.unpaid) {
            throw new BadRequestException("Cannot change payment status from PAID to UNPAID. Paid orders cannot be reverted.");
        }
        
        OrderDTO updatedOrder = orderService.updatePaymentStatus(id, paymentStatus);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment status updated successfully", updatedOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrder(
            @PathVariable Integer id,
            @RequestBody OrderDTO orderDTO) {
        
        OrderDTO currentOrder = orderService.getOrderById(id);
        
        if (orderDTO.getPaymentStatus() != null
                && currentOrder.getPaymentStatus() == Order.PaymentStatus.paid 
                && orderDTO.getPaymentStatus() == Order.PaymentStatus.unpaid) {
            throw new BadRequestException("Cannot change payment status from PAID to UNPAID. Paid orders cannot be reverted.");
        }
        
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order updated successfully", updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order deleted successfully", null));
    }

    @GetMapping("/statistics/today")
    public ResponseEntity<ApiResponse<java.util.Map<String, Object>>> getTodayStatistics() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        
        List<OrderDTO> todayOrders = orderService.getOrdersByDateRange(startOfDay, endOfDay);
        
        // Calculate statistics
        long totalOrders = todayOrders.size();
        long pendingOrders = todayOrders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.pending)
                .count();
        long completedOrders = todayOrders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.served)
                .count();
        long paidOrders = todayOrders.stream()
                .filter(o -> o.getPaymentStatus() == Order.PaymentStatus.paid)
                .count();
        
        var statistics = new java.util.HashMap<String, Object>();
        statistics.put("totalOrders", totalOrders);
        statistics.put("pendingOrders", pendingOrders);
        statistics.put("completedOrders", completedOrders);
        statistics.put("paidOrders", paidOrders);
        statistics.put("orders", todayOrders);
        
        return ResponseEntity.ok(new ApiResponse<>(true, "Today's statistics retrieved successfully", statistics));
    }
}

