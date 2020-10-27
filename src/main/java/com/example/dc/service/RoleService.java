package com.example.dc.service;

import com.example.dc.entity.user.RoleEntity;
import com.example.dc.from.RoleMenuSaveFrom;
import com.example.dc.utils.ElAdminResultBeans;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:08 2020/9/27
 * @ Description：
 */
public interface RoleService {
    List<String> getUserAuth(int userId, String userName);

    ElAdminResultBeans getRoleList(String roleName);

    ElAdminResultBeans saveOrEdit(RoleEntity roleEntity);

    ElAdminResultBeans deletedRole(Integer id);

    ElAdminResultBeans roleMenuList(Integer roleId);

    ElAdminResultBeans allRoleMenuList();

    ElAdminResultBeans saveRoleMenu(RoleMenuSaveFrom saveFrom);
}