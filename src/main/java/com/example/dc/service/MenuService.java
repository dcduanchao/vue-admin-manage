package com.example.dc.service;

import com.example.dc.entity.user.MenuEntity;
import com.example.dc.utils.ElAdminResultBeans;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:08 2020/9/27
 * @ Description：
 */
public interface MenuService {

    ElAdminResultBeans getMenuTree(String menuName);

    ElAdminResultBeans getHomeMenuTree();

    ElAdminResultBeans saveOrEdit(MenuEntity menuEntity);

    ElAdminResultBeans deletedMenu(Integer id);


}