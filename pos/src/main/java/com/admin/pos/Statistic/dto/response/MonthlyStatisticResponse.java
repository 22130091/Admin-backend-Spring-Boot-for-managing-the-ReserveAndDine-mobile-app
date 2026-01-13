package com.admin.pos.Statistic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class MonthlyStatisticResponse {
    private String monthLabel;
    private BigDecimal revenue;
}
