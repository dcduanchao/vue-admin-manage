package com.example.dc.dao;

import com.example.dc.entity.home.MallShopEntity;
import com.example.dc.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer>, JpaSpecificationExecutor<UserEntity> {

    UserEntity findByUserName(String userName);

    List<UserEntity> findByUserNameLike(String userName);

}