package com.admin.pos.Statistic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableStatisticResponse {
    private String tableCode;
    private Long orderCount;
    private BigDecimal totalRevenue;
}
