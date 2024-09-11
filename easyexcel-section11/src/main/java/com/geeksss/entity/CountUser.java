package com.geeksss.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class CountUser {

    @ExcelProperty(value = {"七月","访问数"})
    private Integer count;

    @ExcelProperty(value = {"七月","用户访问数"})
    private Integer userCount;

}
