package com.example.dc.service;

import com.example.dc.utils.ElAdminResultBeans;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:38 2020/11/3
 * @ Description：
 */
public interface ImageFileService {
    ElAdminResultBeans getImageList();

    ElAdminResultBeans uplaod(MultipartFile file, HttpServletRequest request) throws IOException;


}
