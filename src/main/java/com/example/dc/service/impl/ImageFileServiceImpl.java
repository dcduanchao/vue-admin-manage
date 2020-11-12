package com.example.dc.service.impl;

import cn.hutool.core.io.IoUtil;
import com.example.dc.dao.ImageFileRepository;
import com.example.dc.dao.MinioFileRepository;
import com.example.dc.enmus.ContentTypeEnum;
import com.example.dc.enmus.MinioBucketTypeEnum;
import com.example.dc.entity.file.ImageFileEntity;
import com.example.dc.entity.file.MinioFileEntity;
import com.example.dc.service.ImageFileService;
import com.example.dc.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.cs.UTF_32LE_BOM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:38 2020/11/3
 * @ Description：
 */
@Service
public class ImageFileServiceImpl implements ImageFileService {

    private   static  String FILE_PATH = "/root/mall/file/";

    private   static  String PATH = "http://49.232.1.162:9090/root/mall/file/";

    @Value("${minio.url}")
    private String minioUrl;

    @Autowired
    private ImageFileRepository imageFileRepository;

    @Autowired
    private MinioFileRepository minioFileRepository;
    @Autowired
    private MinioClientUtils minioClientUtils;



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

    @Override
    public ElAdminResultBeans getMinioFileList() {
        List<MinioFileEntity> all = minioFileRepository.findAll();
        return ResponseUtils.success(all);
    }

    @Override
    public ElAdminResultBeans minioUplaod(MultipartFile file, MinioFileEntity minioFileEntity) throws Exception {
        Integer authType = minioFileEntity.getAuthType();
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString().replace("-","");
        originalFilename=uuid+"_"+originalFilename;
        minioClientUtils.putObject(authType,originalFilename, ContentTypeEnum.OCTET_STREAM.getValue(),file.getInputStream());
        String bucketName = MinioBucketTypeEnum.getValueEnum(authType).getDesc();

        String url = minioUrl+bucketName+"/"+originalFilename;
        if(authType.equals(MinioBucketTypeEnum.PRIVATE_BUCKET.getValue())){
            minioFileEntity.setUrl("-");
        }else {
            minioFileEntity.setUrl(url);
        }

        minioFileEntity.setBucketName(bucketName);
        minioFileEntity.setFileObjectName(originalFilename);
        minioFileEntity.setFileType(fileType);
        minioFileRepository.save(minioFileEntity);
        return  ResponseUtils.success();
    }

    @Override
    public ElAdminResultBeans minioDelete(Integer id) throws Exception {

        MinioFileEntity minioFileEntity = minioFileRepository.findById(id).get();
        minioClientUtils.removeObject(minioFileEntity.getBucketName(),minioFileEntity.getFileObjectName());
        minioFileRepository.delete(minioFileEntity);
        return ResponseUtils.success();
    }

    @Override
    public void minioDownload(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{

        MinioFileEntity minioFileEntity = minioFileRepository.findById(id).get();
        InputStream object = minioClientUtils.getObject(minioFileEntity.getBucketName(), minioFileEntity.getFileObjectName());


        FileUtils.downLoadStreamFile(request,response,object,minioFileEntity.getFileObjectName());
    }
}