package com.example.dc.dao;

import com.example.dc.entity.user.RoleEntity;
import com.example.dc.entity.user.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity,Integer>, JpaSpecificationExecutor<RoleMenuEntity> {


    List<RoleMenuEntity> findByRoleIdIn(List<Integer> roleIds);

    List<RoleMenuEntity> findByRoleId(Integer roleId);

    @Modifying
    @Query(value = "DELETE  FROM role_menu WHERE role_id=?1",nativeQuery = true)
    void deleteByRoleId(Integer roleId);
}