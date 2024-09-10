package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.geeksss.model.UserInfoModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class EasyExcelTest {

    /**
     * 测试：模型映射导出。
     */
    @Test
    @SneakyThrows
    public void testExport() {
        // 获取：用户信息列表。
        List<UserInfoModel> list = this.getList();
        // 处理：导出数据。
        EasyExcel.write("E:\\export.xlsx")
                .sheet("导出数据")
                .head(UserInfoModel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(list);
    }

    /**
     * 获取用户信息列表。
     *
     * @return 返回结果。
     */
    @SneakyThrows
    private List<UserInfoModel> getList() {
        UserInfoModel user1 = new UserInfoModel();
        user1.setUserName("郭德纲"); // 用户姓名。
        user1.setUserGender(1); // 用户性别。
        user1.setUserBirth(DateUtils.parseDate("1973-01-18")); // 用户生日。
        user1.setUserScore(100); // 用户积分。
        user1.setUserReward(BigDecimal.valueOf(123.45)); // 用户佣金。

        UserInfoModel user2 = new UserInfoModel();
        user2.setUserName("于谦"); // 用户姓名。
        user2.setUserGender(2); // 用户性别。
        user2.setUserBirth(DateUtils.parseDate("1967-12-06")); // 用户生日。
        user2.setUserScore(200); // 用户积分。
        user2.setUserReward(BigDecimal.valueOf(234.56)); // 用户佣金。

        UserInfoModel user3 = new UserInfoModel();
        user3.setUserName("岳云鹏"); // 用户姓名。
        user3.setUserGender(0); // 用户性别。
        user3.setUserBirth(DateUtils.parseDate("1985-09-17")); // 用户生日。
        user3.setUserScore(300); // 用户积分。
        user3.setUserReward(BigDecimal.valueOf(345.67)); // 用户佣金。

        return Arrays.asList(user1, user2, user3);
    }

}
