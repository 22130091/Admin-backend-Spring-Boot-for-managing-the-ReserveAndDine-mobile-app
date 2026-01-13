package com.admin.pos.Statistic.controller;

import com.admin.pos.Payload.ApiResponse;
import com.admin.pos.Statistic.dto.TableStatisticDTO;
import com.admin.pos.Statistic.dto.response.MonthlyDataDTO;
import com.admin.pos.Statistic.dto.response.MonthlyStatisticResponse;
import com.admin.pos.Statistic.dto.response.TableStatisticResponse;
import com.admin.pos.Statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/statistics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/tables")
    public ResponseEntity<ApiResponse<List<TableStatisticResponse>>> getTableStats() {
        List<TableStatisticResponse> stats = statisticService.getTableStatistics();
        return ResponseEntity.ok(new ApiResponse<>(true, "Thống kê doanh thu theo bàn đã được trích xuất thành công", stats));
    }

    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<List<MonthlyDataDTO>>> getMonthlyStats() {
        try {
            List<MonthlyDataDTO> stats = statisticService.getMonthlyStats();
            ApiResponse<List<MonthlyDataDTO>> response = new ApiResponse<>(
                    true,
                    "Thống kê doanh thu theo tháng đã được trích xuất thành công",
                    stats
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(
                    false,
                    "Lỗi server: " + e.getMessage(),
                    null
            ));
        }
    }
}