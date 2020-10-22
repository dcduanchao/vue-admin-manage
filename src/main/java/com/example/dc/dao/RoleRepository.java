package com.example.dc.dao;

import com.example.dc.entity.user.RoleEntity;
import com.example.dc.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface RoleRepository extends JpaRepository<RoleEntity,Integer>, JpaSpecificationExecutor<RoleEntity> {



    List<RoleEntity> findByNameLike(String roleName);
}