package com.example.dc.utils;

import com.example.dc.enmus.MinioBucketTypeEnum;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 11:23 2020/11/5
 * @ Description：publicBucket minio 配置 prefix “*” 链接可以永久直接访问  上传永久访问必须在下面文件
 */
@Configuration
@Component
public class MinioClientUtils {


    private final Logger logger = LoggerFactory.getLogger(MinioClientUtils.class);

    @Value("${minio.url}")
    private String url;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.public.bucket}")
    private String publicBucket;

    @Value("${minio.private.bucket}")
    private String privateBucket;

    private  MinioClient minioClient=null;

    /**
     * 文件url前半段
     *
     * @param bucket 桶
     * @return 前半段
     */
    public  String getObjectPrefixUrl(String bucket) {
        return String.format("%s%s/", url, bucket);
    }



    /**
     *
     *@描述  创建连接

     *@创建人  duanchao

     *@创建时间  2020/11/5 14:17
     *
     */
    public MinioClient minioClient() {
        MinioClient minioClient = this.minioClient;
        if(null!=minioClient){
            return this.minioClient;
        }else {
            this.minioClient =  MinioClient.builder()
                    .endpoint(url)
                    .credentials(accessKey, secretKey)
                    .build();
        }
        return this.minioClient;
    }


    /***
     * Bucket命名规范只能包括小写字母，数字和短横线（-）
     * 必须以小写字母或者数字开头
     * 长度必须在3-63字节之间
     * @param bucketName
     * @return
     * @throws Exception
     */
    public boolean makeBucket(String bucketName) throws Exception {
        MinioClient minioClient = minioClient();

        BucketExistsArgs bucketExistsArgs = BucketExistsArgs
                .builder()
                .bucket(bucketName)
                .build();
        boolean exists = minioClient.bucketExists(bucketExistsArgs);
        if (exists) {
            return true;
        }
        MakeBucketArgs makeBucketArgs = MakeBucketArgs
                .builder()
                .bucket(bucketName)
                .build();

        try {
            minioClient.makeBucket(makeBucketArgs);
            return  true;
        } catch (Exception e) {
            logger.error("创建桶出错={}",e);
            throw  new Exception(e);
        }
    }



    /**
     *
     *@描述 列出所有存储桶。

     *@创建人  duanchao

     *@创建时间  2020/11/5 14:18
     *
     */
    public List<Bucket> listBuckets() throws Exception {
        MinioClient minioClient = minioClient();
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets;

    }

    /**
     *
     *@描述  是否存在

     *@创建人  duanchao

     *@创建时间  2020/11/5 14:23
     *
     */
    public boolean bucketExists(String bucketName) throws Exception{
        MinioClient minioClient = minioClient();
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs
                .builder()
                .bucket(bucketName)
                .build();
        return minioClient.bucketExists(bucketExistsArgs);
    }

    /**
     *
     *@描述  移除  只有为空才能删除

     *@创建人  duanchao

     *@创建时间  2020/11/5 14:25
     *
     */
    public void removeBucket(String bucketName)throws Exception{

        MinioClient minioClient = minioClient();
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs
                .builder()
                .bucket(bucketName)
                .build();
        boolean exists = minioClient.bucketExists(bucketExistsArgs);
        if(exists){
            RemoveBucketArgs removeBucketArgs = RemoveBucketArgs.builder().bucket(bucketName).build();
            // 删除`my-bucketname`存储桶，注意，只有存储桶为空时才能删除成功。
            minioClient.removeBucket(removeBucketArgs);
        }
    }

    /**
     *
     *@描述   type:通常类型 公有  私有
     * objectName  存储名称  如果有/ 为多级目录
     *@创建人  duanchao
     *@创建时间  2020/11/6 10:08
     *
     */

     public  void putObject(  Integer type, String objectName, String contentType, InputStream stream)throws Exception{

         MinioClient minioClient = minioClient();
         String bucketName = "";
         if(null==type ||  MinioBucketTypeEnum.PUBLIC_BUCKET.getValue().equals(type)){
             bucketName = publicBucket;
         }else {
             bucketName = privateBucket;
         }

         PutObjectArgs putObjectArgs = PutObjectArgs
                 .builder()
                 .bucket(bucketName)
                 .object(objectName)
                 .stream(stream,stream.available(),0)
                 .contentType(contentType)
                 .build();
         ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);

     }

    public  void putObject(String bucketName ,String objectName, String contentType, InputStream stream)throws Exception{

        MinioClient minioClient = minioClient();
        PutObjectArgs putObjectArgs = PutObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream,stream.available(),0)
                .contentType(contentType)
                .build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);

    }


    /**
      *
      *@描述  路径上传

      *@创建人  duanchao

      *@创建时间  2020/11/6 10:35
      *
      */
     public  void  uploadByPath (Integer type, String objectName, String contentType ,String filePath)throws Exception{
         File file = new File(filePath);
         InputStream inputStream = new FileInputStream(file);
         putObject(type,objectName,contentType,inputStream);
     }

    public  void  uploadByPathDefaultName (Integer type, String contentType ,String filePath)throws Exception{
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        putObject(type,file.getName(),contentType,inputStream);
    }


    public  void  uploadByFile (Integer type, String objectName, String contentType ,File file)throws Exception{
        InputStream inputStream = new FileInputStream(file);
        putObject(type,objectName,contentType,inputStream);
    }


    public  void  uploadByFile (Integer type, String objectName, String contentType , MultipartFile file)throws Exception{
        InputStream inputStream = file.getInputStream();
        putObject(type,objectName,contentType,inputStream);
    }


    public  String  uploadFile(String bucket, String objectKey, String filePath,String contentType ) throws Exception {
        MinioClient minioClient = minioClient();
        minioClient.uploadObject(UploadObjectArgs
                .builder().bucket(bucket)
                .object(objectKey).filename(filePath)
                .contentType(contentType).build());
        return getObjectPrefixUrl(bucket) + objectKey;
    }


    /**
     *
     *@描述 列出某个存储桶中的所有对象。

     *@创建人  duanchao

     *@创建时间  2020/11/6 13:09
     *
     */
    //TODO 返回处理
    public void listObjects(String bucketName) throws Exception{

        MinioClient minioClient = minioClient();
        ListObjectsArgs listObjectsArgs = ListObjectsArgs
                .builder()
                .bucket(bucketName)
                .recursive(true)
                .build();
        Iterable<Result<Item>> results = minioClient.listObjects(listObjectsArgs);

        for (Result<Item> result : results) {
            Item item = result.get();
            System.out.println(  ", " + item.size() + ", " + item.objectName());
        }
    }


    /**
     *
     *@描述 得到文件流

     *@创建人  duanchao

     *@创建时间  2020/11/6 13:19
     *
     */
    public InputStream getObject(String bucketName, String objectName)throws Exception {
        MinioClient minioClient = minioClient();
        ObjectStat objectStat = statObject(bucketName, objectName);
        if(null==objectStat){
            throw  new Exception("文件不存在！！！");
        }

        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();
        InputStream object = minioClient.getObject(getObjectArgs);
        return object;

    }

    /**
     * 
     *@描述  最新 fileName 无法设定
    
     *@创建人  duanchao 下载并将文件保存到本地。
    
     *@创建时间  2020/11/6 13:19
     * 
     */
    @Deprecated
    public void  getObject(String bucketName, String objectName, String fileName) throws Exception{

        MinioClient minioClient = minioClient();

//        DownloadObjectArgs build = DownloadObjectArgs.builder().filename(fileName).bucket(bucketName).object(objectName).build();
//
//        InputStream object = minioClient.getObject(build);

//        GetObjectArgs getObjectArgs = GetObjectArgs
//                .builder()
//                .bucket(bucketName)
//                .object(objectName).build();
//        InputStream object = minioClient.getObject(getObjectArgs);

        minioClient.getObject(bucketName,objectName,fileName);

        
    }


    /**
     *
     *@描述  获取对象的元数据。

     *@创建人  duanchao

     *@创建时间  2020/11/6 13:44
     *
     */
    public ObjectStat statObject(String bucketName, String objectName) throws Exception{

        MinioClient minioClient = minioClient();
        StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(bucketName).object(objectName).build();
        ObjectStat objectStat = minioClient.statObject(statObjectArgs);
        return  objectStat;
    }
    
    /**
     * 
     *@描述  删除一个对象。
    
     *@创建人  duanchao
    
     *@创建时间  2020/11/6 13:49
     * 
     */
    public void removeObject(String bucketName, String objectName)  throws Exception{

        MinioClient minioClient = minioClient();
        RemoveObjectArgs build = RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build();
        minioClient.removeObject(build);
    }


    /**
     *
     *@描述  删除多个对象。

     *@创建人  duanchao

     *@创建时间  2020/11/6 14:00
     *
     */
    public void removeObjects(String bucketName, List<String> objectNames)  throws Exception{
        MinioClient minioClient = minioClient();
        List<DeleteObject> objects = objectNames.stream().map(DeleteObject::new).collect(Collectors.toList());
        RemoveObjectsArgs build = RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build();
        minioClient.removeObjects(build);
    }


    /**
     *
     *@描述  有限时间访问链接  最多7天

     *@创建人  duanchao

     *@创建时间  2020/11/6 14:38
     *
     */
    public  String getSignedUrl(String bucket, String objectKey, int expires) throws Exception {
        MinioClient minioClient = minioClient();
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(objectKey)
                .expiry(expires)
                .build());
    }

}