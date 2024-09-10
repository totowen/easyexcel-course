package com.geeksss;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.data.*;
import com.geeksss.entity.UserInfoEntity;
import com.geeksss.model.UserInfoModel;
import com.geeksss.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        EasyExcel.write("D:\\export.xlsx")
                .sheet("用户信息")
                .head(UserInfoEntity.class)
                .doWrite(list);
    }

    @Test
    public void test3() {
        // 查询：用户信息实体。
        List<UserInfoEntity> entityList = this.userInfoService.list();

        // 转换：entity -> model。
        List<UserInfoModel> modelList = IntStream.range(0, entityList.size()).mapToObj(index -> {
            // 提取：当前用户信息实体。
            UserInfoEntity entity = entityList.get(index);

            // 封装：用户信息模型。
            UserInfoModel model = new UserInfoModel();
            // 行号。
            model.setRowNumber(index + 1);
            // 用户标识。
            CommentData commentData = new CommentData();
            commentData.setAuthor("张德龙");
            commentData.setRichTextStringData(new RichTextStringData("这是批注内容"));
            WriteCellData<Long> id = new WriteCellData<>(BigDecimal.valueOf(entity.getId()));
            id.setCommentData(commentData);
            model.setId(id);
            // 用户昵称。
            HyperlinkData hyperlinkData = new HyperlinkData();
            hyperlinkData.setHyperlinkType(HyperlinkData.HyperlinkType.URL);
            hyperlinkData.setAddress("http://www.xxx.com/user/" + entity.getId());
            WriteCellData<String> userNickname = new WriteCellData<>(entity.getUserNickname());
            userNickname.setHyperlinkData(hyperlinkData);
            model.setUserNickname(userNickname);
            // 用户性别。
            model.setUserGender(entity.getUserGender());
            // 用户生日。
            model.setUserBirth(entity.getUserBirth());
            // 用户年龄。
            FormulaData formulaData = new FormulaData();
            formulaData.setFormulaValue("CONCAT(DATEDIF(E" + (index + 2) + ", NOW(), \"Y\"), \"岁\")");
            WriteCellData<Void> userAge = new WriteCellData<>();
            userAge.setFormulaData(formulaData);
            model.setUserAge(userAge);
            return model;
        }).collect(Collectors.toList());

        // 导出：用户信息模型。
        EasyExcel.write("D:\\export.xlsx")
                .sheet("用户信息")
                .head(UserInfoModel.class)
                .doWrite(modelList);
    }

    @Test
    public void test4() {
        IntStream.range(0, 10).forEach(index -> {
            log.info("index: {}", index);
        });
    }

}
