package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.geeksss.excel.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
public class EasyExcelTest {

    @Test
    public void testFile() {
        File file = new File("E:\\user-list.xlsx");
        List<Object> list = EasyExcel.read(file).sheet(0).doReadSync();
        for (Object item : list) {
            log.info("item: {}", item);
        }
    }

    @Test
    public void testInputStream() {
        InputStream inputStream = EasyExcelTest.class.getClassLoader().getResourceAsStream("user-list.xlsx");
        List<Map<Integer, Object>> list = EasyExcel.read(inputStream).sheet(0).doReadSync();
        for (Map<Integer, Object> item : list) {
            log.info("昵称: {}, 性别: {}, 生日: {}, 邮箱: {}, 积分: {}, 排名: {}", item.get(0), item.get(1), item.get(2), item.get(3), item.get(4), item.get(5));
        }
    }

    @Test
    public void testInputStreamWithModel() {
        InputStream inputStream = EasyExcelTest.class.getClassLoader().getResourceAsStream("user-list.xlsx");
        List<UserInfoModel> list = EasyExcel.read(inputStream).sheet(0).head(UserInfoModel.class).doReadSync();
        for (UserInfoModel item : list) {
            log.info("昵称: {}, 性别: {}, 生日: {}, 邮箱: {}, 积分: {}, 排名: {}", item.getUserName(), item.getUserGender(), item.getUserBirth(), item.getUserEmail(), item.getUserScore(), item.getUserRank());
        }
    }

}
