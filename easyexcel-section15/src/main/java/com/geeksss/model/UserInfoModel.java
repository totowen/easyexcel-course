package com.geeksss.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户信息，模型。
 */
@Data
public class UserInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号。
     */
    private Integer userCode;

    /**
     * 用户昵称。
     */
    private String userNickname;

    /**
     * 用户积分。
     */
    private Integer userScore;

    /**
     * 用户佣金。
     */
    private BigDecimal userReward;

}
