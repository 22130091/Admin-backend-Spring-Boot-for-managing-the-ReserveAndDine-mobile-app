package com.admin.pos.Statistic.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyDataDTO {
    private String monthLabel;
    private Double revenue;
}