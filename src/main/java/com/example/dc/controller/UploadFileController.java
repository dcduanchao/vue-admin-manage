package com.example.dc.controller;

import com.example.dc.ann.AnonymousAccess;
import com.example.dc.service.ImageFileService;
import com.example.dc.utils.ElAdminResultBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:33 2020/11/3
 * @ Description：
 */
@RestController
@RequestMapping("/file")
public class UploadFileController {

    @Autowired
    private ImageFileService imageFileService;

    @GetMapping("/image/list")
    public ElAdminResultBeans getImageList() {
        ElAdminResultBeans beans = imageFileService.getImageList();
        return beans;
    }


    @AnonymousAccess
    @PostMapping("/upload")
    public ElAdminResultBeans upload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        ElAdminResultBeans beans = imageFileService.uplaod(file,request);
        return beans;

    }
}