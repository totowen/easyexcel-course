package com.geeksss.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * 用户信息，性别，转换器。
 *
 * @author 张德龙
 */
public class UserInfoGenderConverter implements Converter<Integer> {
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String value = cellData.getStringValue();
        switch (value) {
            case "男":
                return 1;
            case "女":
                return 2;
            default:
                return 0;
        }
    }
}
