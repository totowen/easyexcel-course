package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.geeksss.handler.CustomMergeStrategy;
import com.geeksss.model.UserInfoModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * EasyExcel 测试类。
 *
 * @author 张德龙
 */
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
        EasyExcel.write("G:\\code\\study\\easyexcel-course\\export.xlsx")
                .sheet("导出数据")
                .head(UserInfoModel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                // .registerWriteHandler(new OnceAbsoluteMergeStrategy(2, 3, 0, 0))
                // .registerWriteHandler(new LoopMergeStrategy(2, 0))
                .registerWriteHandler(new CustomMergeStrategy(Arrays.asList("teamName", "userGender")))
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
        user1.setTeamName("第一小队"); // 团队名称。
        user1.setUserName("郭德纲"); // 用户姓名。
        user1.setUserGender(1); // 用户性别。
        user1.setUserBirth(DateUtils.parseDate("1973-01-18")); // 用户生日。
        user1.setUserScore(100); // 用户积分。
        user1.setUserReward(BigDecimal.valueOf(123.45)); // 用户佣金。

        UserInfoModel user2 = new UserInfoModel();
        user2.setTeamName("第一小队"); // 团队名称。
        user2.setUserName("于谦"); // 用户姓名。
        user2.setUserGender(2); // 用户性别。
        user2.setUserBirth(DateUtils.parseDate("1969-01-24")); // 用户生日。
        user2.setUserScore(200); // 用户积分。
        user2.setUserReward(BigDecimal.valueOf(234.56)); // 用户佣金。

        UserInfoModel user3 = new UserInfoModel();
        user3.setTeamName("第二小队"); // 团队名称。
        user3.setUserName("岳云鹏"); // 用户姓名。
        user3.setUserGender(1); // 用户性别。
        user3.setUserBirth(DateUtils.parseDate("1985-04-15")); // 用户生日。
        user3.setUserScore(300); // 用户积分。
        user3.setUserReward(BigDecimal.valueOf(345.67)); // 用户佣金。

        UserInfoModel user4 = new UserInfoModel();
        user4.setTeamName("第二小队"); // 团队名称。
        user4.setUserName("孙越"); // 用户姓名。
        user4.setUserGender(2); // 用户性别。
        user4.setUserBirth(DateUtils.parseDate("1979-10-13")); // 用户生日。
        user4.setUserScore(400); // 用户积分。
        user4.setUserReward(BigDecimal.valueOf(456.78)); // 用户佣金。

        UserInfoModel user5 = new UserInfoModel();
        user5.setTeamName("第三小队"); // 团队名称。
        user5.setUserName("郭麒麟"); // 用户姓名。
        user5.setUserGender(2); // 用户性别。
        user5.setUserBirth(DateUtils.parseDate("1996-02-08")); // 用户生日。
        user5.setUserScore(500); // 用户积分。
        user5.setUserReward(BigDecimal.valueOf(567.89)); // 用户佣金。

        return Arrays.asList(user1, user2, user3, user4, user5);
    }

}
