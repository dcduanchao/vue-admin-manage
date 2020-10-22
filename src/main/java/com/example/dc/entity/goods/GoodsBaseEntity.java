package com.example.dc.entity.goods;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:47 2020/9/14
 * @ Description：
 */
@Entity
@Table(name = "goods_base", schema = "test-mall", catalog = "")
public class GoodsBaseEntity {
    private int id;
    private String title;
    private Double price;
    private Double oldPrice;
    private String discountType;
    private Integer salesCount;
    private Integer collCount;
    private String serverType;
    private Integer goodsId;

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
    @Column(name = "title", nullable = true, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "old_price", nullable = true, precision = 2)
    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    @Basic
    @Column(name = "discount_type", nullable = true, length = 100)
    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @Basic
    @Column(name = "sales_count", nullable = true)
    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    @Basic
    @Column(name = "coll_count", nullable = true)
    public Integer getCollCount() {
        return collCount;
    }

    public void setCollCount(Integer collCount) {
        this.collCount = collCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsBaseEntity that = (GoodsBaseEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(price, that.price) &&
                Objects.equals(oldPrice, that.oldPrice) &&
                Objects.equals(discountType, that.discountType) &&
                Objects.equals(salesCount, that.salesCount) &&
                Objects.equals(collCount, that.collCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, oldPrice, discountType, salesCount, collCount);
    }

    @Basic
    @Column(name = "server_type", nullable = true, length = 100)
    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    @Basic
    @Column(name = "goods_id", nullable = true)
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}