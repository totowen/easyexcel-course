package com.geeksss.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.geeksss.excel.model.UserInfoModel;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "Hello World!";
    }

    @PostMapping("/upload")
    @SneakyThrows
    public List<UserInfoModel> upload(MultipartFile file) {
        // 获取：文件输入流。
        InputStream inputStream = file.getInputStream();
        // 解析：内容。
        return EasyExcel.read(inputStream).head(UserInfoModel.class).sheet(0).headRowNumber(6).doReadSync();
    }

}
