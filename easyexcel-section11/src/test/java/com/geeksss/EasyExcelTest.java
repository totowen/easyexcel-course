package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geeksss.entity.UserInfoEntity;
import com.geeksss.service.IUserInfoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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

    @Resource
    private IUserInfoService userInfoService;

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

        EasyExcel.write("G:\\code\\study\\easyexcel-course\\export2.xlsx")
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
        headList.add(Arrays.asList("账号信息", "用户手机"));
        headList.add(Arrays.asList("账号信息", "用户邮箱"));

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
