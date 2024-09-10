package com.geeksss.excel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 读取错误，模型。
 */
@Data
@AllArgsConstructor
public class ReadErrorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行索引。
     */
    private Integer rowIndex;

    /**
     * 列索引。
     */
    private Integer columnIndex;

    /**
     * 单元格数据。
     */
    private String cellData;

    /**
     * 错误信息。
     */
    private String message;

}
