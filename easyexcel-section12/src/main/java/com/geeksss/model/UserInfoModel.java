package com.geeksss.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息，模型类。
 */
@Data
@ColumnWidth(value = 20)
public class UserInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行号。
     */
    @ExcelProperty(value = "行号")
    private Integer rowNumber;

    /**
     * 用户标识。
     */
    @ExcelProperty(value = "用户标识")
    private WriteCellData<Long> id;

    /**
     * 用户昵称。
     */
    @ExcelProperty(value = "用户昵称")
    private WriteCellData<String> userNickname;

    /**
     * 用户性别。
     */
    @ExcelProperty(value = "用户性别")
    private Integer userGender;

    /**
     * 用户生日。
     */
    @ExcelProperty(value = "用户生日")
    private Date userBirth;

    /**
     * 用户年龄。
     */
    @ExcelProperty(value = "用户年龄")
    private WriteCellData<Void> userAge;

}
