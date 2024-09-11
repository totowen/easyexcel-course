package com.geeksss.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class MonthData {

    @ExcelProperty(value = {"月份数据", "月份名称"})
    private String monthName;

    @ExcelProperty(value = {"月份数据", "数据 1 名称"})
    private int data1;

    @ExcelProperty(value = {"月份数据", "数据 2 名称"})
    private double data2;
}