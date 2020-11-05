package com.example.dc.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:45 2020/11/4
 * @ Description：
 */
public class FileUtils {

    public  static File upload (MultipartFile file , String path) throws IOException {
        if(null==file){
            throw  new  IOException("文件不能为空");
        }
        String name = file.getOriginalFilename();
        String newFile = UUID.randomUUID().toString()+"_"+name;
        String path1 = path + newFile;
        File dest = new File(path1).getCanonicalFile();
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        // 文件写入
        file.transferTo(dest);
        return  dest;
    }



    public  static  File  uploadIPServer(MultipartFile file , String path){


        return null;
    }
}