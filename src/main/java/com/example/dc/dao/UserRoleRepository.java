package com.example.dc.dao;

import com.example.dc.entity.user.UserRoleEntity;
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
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer>, JpaSpecificationExecutor<UserRoleEntity> {


    List<UserRoleEntity> findByUserId(int userId);

    List<UserRoleEntity>  findByUserIdIn(List<Integer> userIds);

    @Modifying
    @Query(value = "DELETE  FROM user_role WHERE user_id = ?1",nativeQuery = true)
    void deleteByUserId(Integer id);
}