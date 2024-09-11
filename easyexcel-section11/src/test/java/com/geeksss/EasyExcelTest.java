package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geeksss.entity.*;
import com.geeksss.service.IUserInfoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EasyExcelTest {

    public static List<MainDataEntity> generateData() {
        List<MainDataEntity> dataList = new ArrayList<>();

        MainDataEntity entity1 = new MainDataEntity();
        entity1.setField1("数据 1");
        entity1.setField2(10);
        entity1.setField3(10.5);

        List<MonthData> monthDataList1 = new ArrayList<>();
        MonthData monthData1 = new MonthData();
        monthData1.setMonthName("一月");
        monthData1.setData1(5);
        monthData1.setData2(6.5);
        monthDataList1.add(monthData1);

        MonthData monthData2 = new MonthData();
        monthData2.setMonthName("二月");
        monthData2.setData1(8);
        monthData2.setData2(9.5);
        monthDataList1.add(monthData2);

        entity1.setMonthDataList(monthDataList1);

        MainDataEntity entity2 = new MainDataEntity();
        entity2.setField1("数据 2");
        entity2.setField2(20);
        entity2.setField3(20.5);

        List<MonthData> monthDataList2 = new ArrayList<>();
        MonthData monthData3 = new MonthData();
        monthData3.setMonthName("三月");
        monthData3.setData1(15);
        monthData3.setData2(16.5);
        monthDataList2.add(monthData3);

        MonthData monthData4 = new MonthData();
        monthData4.setMonthName("四月");
        monthData4.setData1(18);
        monthData4.setData2(19.5);
        monthDataList2.add(monthData4);

        entity2.setMonthDataList(monthDataList2);

        dataList.add(entity1);
        dataList.add(entity2);

        return dataList;
    }

    @Resource
    private IUserInfoService userInfoService;

    @Test
    public void test22() {
        // 模拟数据
        List<MainDataEntity> dataList = generateData();

        // 导出文件路径
        String filePath = "G:\\code\\study\\easyexcel-course\\your_file_path.xlsx";

        // 执行导出
        exportData(dataList, filePath);
    }

    public void exportData(List<MainDataEntity> dataList, String filePath) {


    }

    @Test
    public void test1() {

        List<UserInfoEntity> list = this.userInfoService.list();
        for (UserInfoEntity item : list) {
            log.info("item: {}", item);
        }
    }

    @Test
    public void test2() {
        List<UserInfoEntity> list = this.userInfoService.list();

        // 导出文件路径
        String filePath = "G:\\code\\study\\easyexcel-course\\your_file_path.xlsx";

        EasyExcel.write(filePath)
                .sheet("用户信息")
                .head(UserInfoEntity.class)
                .includeColumnFieldNames(Arrays.asList("id", "userNickname", "userPhone", "userEmail"))
                .doWrite(list);
    }

    @Test
    public void test3() {
        List<UserInfoEntity> list = this.userInfoService.list();

        List<List<String>> headList = new ArrayList<>();
        headList.add(Arrays.asList("用户信息", "用户标识"));
        headList.add(Arrays.asList("用户信息", "用户昵称"));
        headList.add(Arrays.asList("七月", "访问用户数"));
        headList.add(Arrays.asList("七月", "访问量"));

        EasyExcel.write("G:\\code\\study\\easyexcel-course\\export3.xlsx")
                .sheet("用户信息")
                .head(headList)
                .includeColumnFieldNames(Arrays.asList("id", "userNickname", "userPhone", "userEmail"))
                .doWrite(list);
    }

    @Test
    public void test4() {
        // 导出字段名称。
        List<String> exportFieldNames = Arrays.asList("id", "userNickname", "userPhone", "userEmail", "userScore", "userReward");

        // 解析字段名称 -> 数据表列名称。
        Map<String, String[]> map = this.resolveFieldNameMap(UserInfoEntity.class, exportFieldNames);

        // 动态查询。
        List<String> columnNames = new ArrayList<>(map.keySet());
        QueryWrapper<UserInfoEntity> queryWrapper = Wrappers.<UserInfoEntity>query().select(columnNames);
        List<UserInfoEntity> list = this.userInfoService.list(queryWrapper);

        // 动态表头。
        List<List<String>> headList = map.values().stream().map(Arrays::asList).collect(Collectors.toList());
        EasyExcel.write("G:\\code\\study\\easyexcel-course\\export4.xlsx")
                .sheet("用户信息")
                .head(headList)
                .doWrite(list);

        // 问题解决：
        // 方法1：使用 .includeColumnFieldNames(exportFieldNames)
        // 方法2：doWrite() 不要给 List<UserInfoEntity>，因为未导出的列会置空，可以转为 List<Map<String, Object>> 去导出。
    }

    /**
     * 动态头部
     */
    @Test
    public void test5() {

        List<UserInfoEntity> list = this.userInfoService.list();

        // 导出文件路径
        String filePath = "G:\\code\\study\\easyexcel-course\\export5.xlsx";

        EasyExcel.write(filePath)
                .sheet("用户信息")
                .head(head())
                .doWrite(list);
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("报表类型");
        List<String> head1 = new ArrayList<>();
        head1.add("二级");
        List<String> head2 = new ArrayList<>();
        head2.add("三级");
        list.add(head0);
        list.add(head1);
        list.add(head2);

        return list;
    }

    /**
     * 动态头部
     */
    @Test
    public void test6() {


        // 导出文件路径
        String filePath = "G:\\code\\study\\easyexcel-course\\export6.xlsx";

        // 合并策略：指定要合并的行列范围
        int[][] toMergeRows = {{2, 3}, {4, 6}};
        int[] toMergeColumns = {0, 1, 2, 3, 8, 9};
        List<CellRangeAddress> list = new ArrayList<>();
        for (int[] toMergeRow : toMergeRows) {
            for (int toMergeColumn : toMergeColumns) {
                list.add(new CellRangeAddress(toMergeRow[0], toMergeRow[1], toMergeColumn, toMergeColumn));
            }
        }

        EasyExcel.write(filePath)
                .head(header())
                .registerWriteHandler(new AssignRowsAndColumnsToMergeStrategy(list))
                .sheet("模板")
                .doWrite(data());
    }

    @Test
    public void test07() {

        // 导出文件路径
        String filePath = "G:\\code\\study\\easyexcel-course\\export7.xlsx";

        EasyExcel.write(filePath)
                .head(header())
                // 合并策略：合并相同数据的行。第一个参数表示从哪一行开始进行合并，由于表头占了两行，因此从第2行开始（索引从0开始）
                // 第二个参数是指定哪些列要进行合并
                .registerWriteHandler(new MergeSameRowsStrategy(2, new int[]{0, 1, 2, 3, 8, 9}))
                // 注意：需要先调用registerWriteHandler()再调用sheet()方法才能使合并策略生效！！！
                .sheet("模板")
                .doWrite(data());
    }

    @Test
    public void test08() {

        // 导出文件路径
        String filePath = "G:\\code\\study\\easyexcel-course\\export8.xlsx";

        EasyExcel.write(filePath)
                .head(header2())
                // 合并策略：合并相同数据的行。
                // 第一个参数表示从哪一行开始进行合并，由于表头占了两行，因此从第2行开始（索引从0开始）
                // 第二个参数是指定哪些列要进行合并
                .registerWriteHandler(new MergeSameRowsStrategy(2, new int[]{0,1}))
                // 注意：需要先调用registerWriteHandler()再调用sheet()方法才能使合并策略生效！！！
                .sheet("模板")
                .doWrite(data());
    }

    /**
     * 创建表头
     */
    private List<List<String>> header2() {
        List<List<String>> headers = new ArrayList<>();
        headers.add(Arrays.asList("报表类型", "报表类型"));
        headers.add(Arrays.asList("二级", "二级"));
        headers.add(Arrays.asList("三级", "三级"));
        headers.add(Arrays.asList("一月", "访问量"));
        headers.add(Arrays.asList("一月", "访问用户数"));
        headers.add(Arrays.asList("二月", "访问量"));
        headers.add(Arrays.asList("二月", "访问用户数"));
        headers.add(Arrays.asList("三月", "访问量"));
        headers.add(Arrays.asList("三月", "访问用户数"));
        return headers;
    }

    /**
     * 创建表头
     */
    private List<List<String>> header() {
        List<List<String>> headers = new ArrayList<>();
        headers.add(Arrays.asList("提交人用户名", "提交人用户名"));
        headers.add(Arrays.asList("提交人姓名", "提交人姓名"));
        headers.add(Arrays.asList("创建时间", "创建时间"));
        headers.add(Arrays.asList("更新时间", "更新时间"));
        headers.add(Arrays.asList("学习经历", "时间"));
        headers.add(Arrays.asList("学习经历", "学校"));
        headers.add(Arrays.asList("学习经历", "专业"));
        headers.add(Arrays.asList("学习经历", "学位"));
        headers.add(Arrays.asList("工作单位", "工作单位"));
        headers.add(Arrays.asList("国籍", "国籍"));
        headers.add(Arrays.asList("获奖经历", "时间"));
        headers.add(Arrays.asList("获奖经历", "何种奖励"));
        return headers;
    }

    /**
     * 创建数据
     */
    private List<List<Object>> data() {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("fengqingyang", "风清扬", "2022-01-25 11:08", "2022-01-25 11:08",
                "2013.9 ~ 2017.7", "华山派", "剑宗", "剑宗高手", "隐居思过崖", "中国", "2015.12", "华山剑法高手"));
        data.add(Arrays.asList("fengqingyang", "风清扬", "2022-01-25 11:08", "2022-01-25 11:08",
                "2017.9 ~ 2020.7", "独孤求败", "独孤剑法", "剑术通神", "隐居思过崖", "中国", "2019.12", "剑法高手"));
        data.add(Arrays.asList("linghuchong", "令狐冲", "2022-01-25 12:08", "2022-01-25 12:08",
                "2020.9 ~ 2024.7", "华山派", "气宗", "气宗庸手", "漂泊江湖", "中国", "2022.12", "华山剑法庸手"));
        data.add(Arrays.asList("linghuchong", "令狐冲", "2022-01-25 12:08", "2022-01-25 12:08",
                "2024.9 ~ 2027.7", "风清扬", "独孤剑法", "剑法高手", "漂泊江湖", "中国", "2025.12", "剑法高手"));
        data.add(Arrays.asList("linghuchong", "令狐冲", "2022-01-25 12:08", "2022-01-25 12:08",
                "2027.9 ~ 2030.7", "少林寺", "易筋经", "内功高手", "漂泊江湖", "中国", "2029.12", "内功高手"));
        return data;
    }



    /**
     * 解析字段名称 -> 数据表列名称。
     *
     * @param clz              目标类型。
     * @param exportFieldNames 字段名称集合。
     * @param <T>              目标类型。
     * @return 返回数据表列名、表头名称。
     */
    @SneakyThrows
    private <T> Map<String, String[]> resolveFieldNameMap(Class<T> clz, List<String> exportFieldNames) {
        Map<String, String[]> map = new LinkedHashMap<>(exportFieldNames.size());

        for (String fieldName : exportFieldNames) {
            Field field = clz.getDeclaredField(fieldName);
            String columnName = field.isAnnotationPresent(TableId.class) ? field.getAnnotation(TableId.class).value() : field.getAnnotation(TableField.class).value();
            String[] headNames = field.getAnnotation(ExcelProperty.class).value();
            map.put(columnName, headNames);
        }

        return map;
    }

}
