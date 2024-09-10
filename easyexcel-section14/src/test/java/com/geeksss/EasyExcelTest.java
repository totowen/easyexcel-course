package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.geeksss.model.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Easy Excel 单元测试。
 */
@Slf4j
public class EasyExcelTest {

    @Test
    public void testMap() {
        // 加载：Excel模板。
        InputStream exportTemplate = EasyExcelTest.class.getClassLoader().getResourceAsStream("export-template.xlsx");

        // 封装：Map对象。
        Map<String, Object> map = new HashMap<>();
        map.put("userCode", 1001);
        map.put("userNickname", "张德龙");
        map.put("userScore", 100);
        map.put("userReward", BigDecimal.valueOf(123.45));

        // 导出：Excel文件。
        EasyExcel.write("D:\\export.xlsx")
                .withTemplate(exportTemplate)
                .sheet()
                .doFill(map);
    }

    @Test
    public void testObject() {
        // 加载：Excel模板。
        InputStream exportTemplate = EasyExcelTest.class.getClassLoader().getResourceAsStream("export-template.xlsx");

        // 封装：Object对象。
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUserCode(1001);
        userInfoModel.setUserNickname("张德龙");
        userInfoModel.setUserScore(200);
        userInfoModel.setUserReward(BigDecimal.valueOf(123.45));

        // 导出：Excel文件。
        EasyExcel.write("D:\\export.xlsx")
                .withTemplate(exportTemplate)
                .sheet()
                .doFill(userInfoModel);
    }

}
