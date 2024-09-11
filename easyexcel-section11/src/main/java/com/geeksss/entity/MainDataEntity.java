package com.geeksss.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class MainDataEntity {

    // 前三列的基本字段
    @ExcelProperty(value = {"基本信息", "字段 1 名称"})
    private String field1;

    @ExcelProperty(value = {"基本信息", "字段 2 名称"})
    private int field2;

    @ExcelProperty(value = {"基本信息", "字段 3 名称"})
    private double field3;

    // 月份数据的集合
    private List<MonthData> monthDataList;
}

