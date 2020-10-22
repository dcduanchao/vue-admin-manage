package com.example.dc.entity.home;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:40 2020/9/15
 * @ Description：
 */
@Entity
@Table(name = "mall_shop", schema = "test-mall", catalog = "")
public class MallShopEntity {
    private int id;
    private String storeName;
    private String storeIcon;
    private Integer goodsCount;
    private Integer salesCount;
    private Double descScore;
    private Integer descBetter;
    private Double priceScore;
    private Integer priceBetter;
    private Double qualityScore;
    private Integer qualityBetter;

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
    @Column(name = "store_name", nullable = true, length = 50)
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Basic
    @Column(name = "store_icon", nullable = true, length = 500)
    public String getStoreIcon() {
        return storeIcon;
    }

    public void setStoreIcon(String storeIcon) {
        this.storeIcon = storeIcon;
    }

    @Basic
    @Column(name = "goods_count", nullable = true)
    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
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
    @Column(name = "desc_score", nullable = true, precision = 2)
    public Double getDescScore() {
        return descScore;
    }

    public void setDescScore(Double descScore) {
        this.descScore = descScore;
    }

    @Basic
    @Column(name = "desc_better", nullable = true)
    public Integer getDescBetter() {
        return descBetter;
    }

    public void setDescBetter(Integer descBetter) {
        this.descBetter = descBetter;
    }

    @Basic
    @Column(name = "price_score", nullable = true, precision = 2)
    public Double getPriceScore() {
        return priceScore;
    }

    public void setPriceScore(Double priceScore) {
        this.priceScore = priceScore;
    }

    @Basic
    @Column(name = "price_better", nullable = true)
    public Integer getPriceBetter() {
        return priceBetter;
    }

    public void setPriceBetter(Integer priceBetter) {
        this.priceBetter = priceBetter;
    }

    @Basic
    @Column(name = "quality_score", nullable = true, precision = 2)
    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    @Basic
    @Column(name = "quality_better", nullable = true)
    public Integer getQualityBetter() {
        return qualityBetter;
    }

    public void setQualityBetter(Integer qualityBetter) {
        this.qualityBetter = qualityBetter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MallShopEntity that = (MallShopEntity) o;
        return id == that.id &&
                Objects.equals(storeName, that.storeName) &&
                Objects.equals(storeIcon, that.storeIcon) &&
                Objects.equals(goodsCount, that.goodsCount) &&
                Objects.equals(salesCount, that.salesCount) &&
                Objects.equals(descScore, that.descScore) &&
                Objects.equals(descBetter, that.descBetter) &&
                Objects.equals(priceScore, that.priceScore) &&
                Objects.equals(priceBetter, that.priceBetter) &&
                Objects.equals(qualityScore, that.qualityScore) &&
                Objects.equals(qualityBetter, that.qualityBetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeName, storeIcon, goodsCount, salesCount, descScore, descBetter, priceScore, priceBetter, qualityScore, qualityBetter);
    }
}