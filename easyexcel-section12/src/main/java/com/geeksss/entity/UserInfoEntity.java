package com.geeksss.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息，实体类。
 */
@Data
@TableName(value = "tb_user_info")
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键标识。
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ExcelProperty(value = {"用户信息", "用户标识"})
    private Long id;

    /**
     * 用户昵称。
     */
    @TableField(value = "user_nickname")
    @ExcelProperty(value = {"用户信息", "用户昵称"})
    private String userNickname;

    /**
     * 用户手机。
     */
    @TableField(value = "user_phone")
    @ExcelProperty(value = {"账号信息", "用户手机"})
    private String userPhone;
    /**
     * 用户邮箱。
     */
    @TableField(value = "user_email")
    @ExcelProperty(value = {"账号信息", "用户邮箱"})
    private String userEmail;

    /**
     * 用户性别。
     */
    @TableField(value = "user_gender")
    @ExcelProperty(value = {"其他信息", "用户性别"})
    private Integer userGender;
    /**
     * 用户生日。
     */
    @TableField(value = "user_birth")
    @ExcelProperty(value = {"其他信息", "用户生日"})
    private Date userBirth;

    /**
     * 用户积分。
     */
    @TableField(value = "user_score")
    @ExcelProperty(value = {"账户信息", "用户积分"})
    private Integer userScore;
    /**
     * 用户佣金。
     */
    @TableField(value = "user_reward")
    @ExcelProperty(value = {"账户信息", "用户佣金"})
    private BigDecimal userReward;

}
