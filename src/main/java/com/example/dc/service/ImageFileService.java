package com.example.dc.service;

import com.example.dc.entity.file.MinioFileEntity;
import com.example.dc.utils.ElAdminResultBeans;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:38 2020/11/3
 * @ Description：
 */
public interface ImageFileService {
    ElAdminResultBeans getImageList();

    ElAdminResultBeans uplaod(MultipartFile file, HttpServletRequest request) throws IOException;


    ElAdminResultBeans getMinioFileList();


    ElAdminResultBeans minioUplaod(MultipartFile file, MinioFileEntity minioFileEntity) throws IOException, Exception;

    ElAdminResultBeans minioDelete(Integer id) throws Exception;

    void minioDownload(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
