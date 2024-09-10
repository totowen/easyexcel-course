package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.geeksss.model.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
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
        Map<String, Object> map1 = new HashMap<>();
        map1.put("userCode", 1001);
        map1.put("userNickname", "张德龙");
        map1.put("userScore", 100);
        map1.put("userReward", BigDecimal.valueOf(123.45));

        Map<String, Object> map2 = new HashMap<>();
        map2.put("userCode", 1002);
        map2.put("userNickname", "李女士");
        map2.put("userScore", 100);
        map2.put("userReward", BigDecimal.valueOf(123.45));

        // 导出：Excel文件。
        EasyExcel.write("D:\\export.xlsx")
                .withTemplate(exportTemplate)
                .sheet()
                .doFill(Arrays.asList(map1, map2));
    }

    @Test
    public void testObject() {
        // 加载：Excel模板。
        InputStream exportTemplate = EasyExcelTest.class.getClassLoader().getResourceAsStream("export-template.xlsx");

        // 封装：Object对象。
        UserInfoModel user1 = new UserInfoModel();
        user1.setUserCode(1001);
        user1.setUserNickname("张德龙");
        user1.setUserScore(200);
        user1.setUserReward(BigDecimal.valueOf(123.45));

        UserInfoModel user2 = new UserInfoModel();
        user2.setUserCode(1002);
        user2.setUserNickname("李女士");
        user2.setUserScore(200);
        user2.setUserReward(BigDecimal.valueOf(123.45));

        // 导出：Excel文件。
        EasyExcel.write("D:\\export.xlsx")
                .withTemplate(exportTemplate)
                .sheet()
                .doFill(Arrays.asList(user1, user2));
    }

}
