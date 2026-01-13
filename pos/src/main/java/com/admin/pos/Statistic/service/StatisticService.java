package com.admin.pos.Statistic.service;

import com.admin.pos.Statistic.dto.TableStatisticDTO;
import com.admin.pos.Statistic.dto.response.MonthlyDataDTO;
import com.admin.pos.Statistic.dto.response.MonthlyStatisticResponse;
import com.admin.pos.Statistic.dto.response.TableStatisticResponse;
import com.admin.pos.Statistic.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public List<TableStatisticResponse> getTableStatistics() {
        List<Object[]> results = statisticRepository.getRawTableStatistics();

        return results.stream().map(row -> TableStatisticResponse.builder()
                .tableCode((String) row[0])
                .orderCount(((Number) row[1]).longValue())
                .totalRevenue((BigDecimal) row[2])
                .build()
        ).collect(Collectors.toList());
    }




    public List<MonthlyDataDTO> getMonthlyStats() {
        List<Object[]> results = statisticRepository.getMonthlyRevenueRaw();
        return results.stream().map(result -> {
            String label = "Tháng " + result[1] + "/" + result[0];
            Double revenue = 0.0;
            if (result[2] != null) {
                revenue = ((Number) result[2]).doubleValue();
            }

            return new MonthlyDataDTO(label, revenue);
        }).collect(Collectors.toList());
    }


}