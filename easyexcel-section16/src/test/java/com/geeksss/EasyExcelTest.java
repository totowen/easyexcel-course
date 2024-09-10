package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Easy Excel 单元测试。
 */
@Slf4j
public class EasyExcelTest {

    @Test
    public void test() {
        InputStream exportTemplate = EasyExcelTest.class.getClassLoader().getResourceAsStream("export-template.xlsx");

        try (ExcelWriter excelWriter = EasyExcel.write("D:\\export.xlsx").withTemplate(exportTemplate).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();

            // 公司信息。
            Map<String, Object> companyInfo = new HashMap<>();
            companyInfo.put("companyName", "北京XXX信息技术有限公司");
            excelWriter.fill(companyInfo, writeSheet);

            // 部门信息。
            Map<String, Object> deptInfo1 = new HashMap<>();
            deptInfo1.put("deptName", "研发部");
            deptInfo1.put("deptMaster", "张先生");
            deptInfo1.put("deptContact", "186****7420");
            Map<String, Object> deptInfo2 = new HashMap<>();
            deptInfo2.put("deptName", "设计部");
            deptInfo2.put("deptMaster", "李女士");
            deptInfo2.put("deptContact", "185****7420");
            List<Map<String, Object>> deptList = Arrays.asList(deptInfo1, deptInfo2);
            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build(); // 填充配置。
            excelWriter.fill(new FillWrapper("dept", deptList), fillConfig, writeSheet); // dept: []

            // 用戶信息。
            Map<String, Object> userInfo1 = new HashMap<>();
            userInfo1.put("userCode", 1001);
            userInfo1.put("userNickname", "张三");
            userInfo1.put("userScore", 100);
            userInfo1.put("userReward", BigDecimal.valueOf(123.45));
            Map<String, Object> userInfo2 = new HashMap<>();
            userInfo2.put("userCode", 1002);
            userInfo2.put("userNickname", "李四");
            userInfo2.put("userScore", 100);
            userInfo2.put("userReward", BigDecimal.valueOf(123.45));
            List<Map<String, Object>> userList = Arrays.asList(userInfo1, userInfo2);
            excelWriter.fill(new FillWrapper("user", userList), writeSheet); // user: []
        }
    }

}
