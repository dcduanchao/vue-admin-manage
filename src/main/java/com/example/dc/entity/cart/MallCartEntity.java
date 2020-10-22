package com.example.dc.entity.cart;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:38 2020/9/16
 * @ Description：
 */
@Entity
@Table(name = "mall_cart", schema = "test-mall", catalog = "")
public class MallCartEntity {
    private int id;
    private Integer goodId;
    private Integer goodsNum;

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
    @Column(name = "good_id", nullable = true)
    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "goods_num", nullable = true)
    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MallCartEntity that = (MallCartEntity) o;
        return id == that.id &&
                Objects.equals(goodId, that.goodId) &&
                Objects.equals(goodsNum, that.goodsNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodId, goodsNum);
    }
}