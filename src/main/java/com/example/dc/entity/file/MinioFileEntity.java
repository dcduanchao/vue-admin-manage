package com.example.dc.entity.file;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ Author     ：duanchao
 * @ Date       ： 11:03 2020/11/9
 * @ Description：
 */
@Entity
@Table(name = "minio_file", schema = "test-mall", catalog = "")
public class MinioFileEntity {
    private int id;
    private String name;
    private String url;
    private Integer authType;
    private String bucketName;
    private String fileObjectName;
    private String fileType;
    private Timestamp updateTime;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "auth_type", nullable = true)
    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    @Basic
    @Column(name = "bucket_name", nullable = true, length = 300)
    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Basic
    @Column(name = "file_object_name", nullable = true, length = 500)
    public String getFileObjectName() {
        return fileObjectName;
    }

    public void setFileObjectName(String fileObjectName) {
        this.fileObjectName = fileObjectName;
    }

    @Basic
    @Column(name = "file_type", nullable = true, length = 30)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinioFileEntity that = (MinioFileEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(authType, that.authType) &&
                Objects.equals(bucketName, that.bucketName) &&
                Objects.equals(fileObjectName, that.fileObjectName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, authType, bucketName, fileObjectName, fileType, updateTime);
    }
}