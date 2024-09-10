package com.geeksss.controller;

import com.alibaba.excel.EasyExcel;
import com.geeksss.Application;
import com.geeksss.entity.UserInfoEntity;
import com.geeksss.service.IUserInfoService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Resource
    private IUserInfoService userInfoService;

    @GetMapping("/list")
    public List<UserInfoEntity> list() {
        return this.userInfoService.list();
    }

    @GetMapping(value = "/export")
    @SneakyThrows
    public void export(HttpServletResponse response) {
        List<UserInfoEntity> list = this.userInfoService.list();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");

        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();

        InputStream exportTemplate = Application.class.getClassLoader().getResourceAsStream("export-template.xlsx");
        EasyExcel.write(outputStream)
                .withTemplate(exportTemplate)
                .sheet()
                .doFill(list);
    }

}
