package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.geeksss.excel.listener.UserInfoReadListener;
import com.geeksss.excel.model.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
        List<UserInfoModel> list = EasyExcel.read(inputStream).head(UserInfoModel.class).headRowNumber(6).sheet(0).doReadSync();
        for (UserInfoModel item : list) {
            log.info("昵称: {}, 性别: {}, 生日: {}, 邮箱: {}, 积分: {}, 排名: {}", item.getUserName(), item.getUserGender(), item.getUserBirth(), item.getUserEmail(), item.getUserScore(), item.getUserRank());
        }
    }

    @Test
    public void testReadListener() {
        InputStream inputStream = EasyExcelTest.class.getClassLoader().getResourceAsStream("user-list.xlsx");
        Predicate<UserInfoModel> predicate = (item) -> item.getUserGender() == 1;
        UserInfoReadListener userInfoReadListener = new UserInfoReadListener(predicate, this::saveBatch);
        EasyExcel.read(inputStream, UserInfoModel.class, userInfoReadListener).sheet(0).doRead();
    }

    private void saveBatch(List<UserInfoModel> list) {
        log.info("批量插入：{}", list.size());
        for (UserInfoModel item : list) {
            log.info("item: {}", item);
        }
    }

}
