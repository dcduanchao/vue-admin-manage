package com.example.dc.dao;

import com.example.dc.entity.user.MenuEntity;
import com.example.dc.entity.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:42 2020/7/3
 * @ Description：
 */
public interface MenuRepository extends JpaRepository<MenuEntity,Integer>, JpaSpecificationExecutor<MenuEntity> {


    List<MenuEntity> findByIdIn(List<Integer> menuIds);

    List<MenuEntity> findByNameLikeOrderBySortAsc(String name);

    List<MenuEntity> findByTypeInOrderBySortAsc(List<Integer> asList);


}