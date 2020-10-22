package com.example.dc.dao;

import com.example.dc.entity.user.RoleEntity;
import com.example.dc.entity.user.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity,Integer>, JpaSpecificationExecutor<RoleMenuEntity> {


    List<RoleMenuEntity> findByRoleIdIn(List<Integer> roleIds);
}