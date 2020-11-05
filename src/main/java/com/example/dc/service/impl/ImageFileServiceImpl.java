package com.example.dc.service.impl;

import com.example.dc.dao.ImageFileRepository;
import com.example.dc.entity.file.ImageFileEntity;
import com.example.dc.service.ImageFileService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.FileUtils;
import com.example.dc.utils.IpUtils;
import com.example.dc.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:38 2020/11/3
 * @ Description：
 */
@Service
public class ImageFileServiceImpl implements ImageFileService {

    private   static  String FILE_PATH = "/root/mall/file/";

    private   static  String PATH = "http://49.232.1.162:9090/root/mall/file/";

    @Autowired
    private ImageFileRepository imageFileRepository;
    @Override
    public ElAdminResultBeans getImageList() {

        List<ImageFileEntity> all = imageFileRepository.findAll();
        return ResponseUtils.success(all);
    }

    @Override
    public ElAdminResultBeans uplaod(MultipartFile file, HttpServletRequest request) throws IOException {


        File upload = FileUtils.upload(file, FILE_PATH);

        String ip = IpUtils.getIp(request);
        String URL = "http://"+ip+"/file/"+upload.getName();
        ImageFileEntity imageFileEntity = new ImageFileEntity();
        imageFileEntity.setName(upload.getName());
        imageFileEntity.setUrl(URL);

        ImageFileEntity save = imageFileRepository.save(imageFileEntity);
        return ResponseUtils.success(save);
    }
}