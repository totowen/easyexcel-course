package com.geeksss.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息，模型。
 */
@Data
public class UserInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户姓名。
     */
    @ExcelProperty(value = {"基本信息", "用户姓名"})
    private String userName;

    /**
     * 用户性别。
     */
    @ExcelProperty(value = {"基本信息", "用户性别"})
    private Integer userGender;

    /**
     * 用户生日。
     */
    @ExcelProperty(value = {"基本信息", "用户生日"})
    @DateTimeFormat(value = "yyyy年MM月dd日")
    @ColumnWidth(value = 20)
    private Date userBirth;

    /**
     * 用户积分。
     */
    @ExcelProperty(value = {"账户信息", "用户积分"})
    private Integer userScore;

    /**
     * 用户佣金。
     */
    @ExcelProperty(value = {"账户信息", "用户佣金"})
    @NumberFormat(value = "￥#.##")
    private BigDecimal userReward;

}
