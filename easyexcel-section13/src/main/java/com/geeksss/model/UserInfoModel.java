package com.geeksss.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import com.geeksss.converters.Base64ImageConverter;
import lombok.Data;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * 用户信息，模型。
 */
@Data
@ContentRowHeight(value = 50)
@ColumnWidth(value = 30)
public class UserInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "File类型")
    private File fileImage;

    @ExcelProperty(value = "byte[]类型")
    private byte[] byteImage;

    @ExcelProperty(value = "InputStream类型")
    private InputStream inputStreamImage;

    @ExcelProperty(value = "URL类型")
    private URL urlImage;

    @ExcelProperty(value = "String类型", converter = Base64ImageConverter.class)
    private String stringImage;

}
