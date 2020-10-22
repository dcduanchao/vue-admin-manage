package com.example.dc.entity.home;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:11 2020/9/8
 * @ Description：
 */
@Entity
@Table(name = "mall_banner", schema = "test-mall", catalog = "")
public class MallBannerEntity {
    private int id;
    private String title;
    private String image;
    private Timestamp createTime;
    private String link;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 500)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MallBannerEntity that = (MallBannerEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(image, that.image) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, image, createTime);
    }

    @Basic
    @Column(name = "link", nullable = true, length = 500)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}