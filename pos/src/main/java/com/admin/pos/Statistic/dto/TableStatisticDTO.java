package com.admin.pos.Statistic.dto;

import java.math.BigDecimal;

public class TableStatisticDTO {
    private String tableCode;
    private Long orderCount;
    private BigDecimal revenue;

    public TableStatisticDTO(String s, long l, BigDecimal bigDecimal) {
        this.tableCode = s;
        this.orderCount = l;
        this.revenue = bigDecimal;
    }
}
