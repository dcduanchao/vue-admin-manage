package com.example.dc.controller;

import com.example.dc.entity.user.MenuEntity;
import com.example.dc.entity.user.RoleEntity;
import com.example.dc.service.MenuService;
import com.example.dc.service.RoleService;
import com.example.dc.utils.ElAdminResultBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:02 2020/10/21
 * @ Description：
 */
@RestController
@RequestMapping("/system")
public class RoleMenuController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/role/list")
    public ElAdminResultBeans getRoleList(String roleName){
        ElAdminResultBeans beans =  roleService.getRoleList(roleName);
        return  beans;
    }

    @PostMapping("/role/addoredit")
    public ElAdminResultBeans addOrEdit(@RequestBody RoleEntity roleEntity){
        ElAdminResultBeans beans =  roleService.saveOrEdit(roleEntity);
        return  beans;
    }


    @GetMapping("/role/deleted")
    public ElAdminResultBeans deletedRole(Integer id){
        ElAdminResultBeans beans =  roleService.deletedRole(id);
        return  beans;
    }


    @GetMapping("/menu/list")
    public ElAdminResultBeans getMenuTree(String menuName){
        ElAdminResultBeans beans = menuService.getMenuTree(menuName);
        return  beans;
    }

    //首页菜单
    @GetMapping("/menu/home/list")
    public ElAdminResultBeans getHomeMenuTree(){
        ElAdminResultBeans beans = menuService.getHomeMenuTree();
        return  beans;
    }

    @PostMapping("/menu/addoredit")
    public ElAdminResultBeans addOrEditMenu(@RequestBody MenuEntity menuEntity){
        ElAdminResultBeans beans =  menuService.saveOrEdit(menuEntity);
        return  beans;
    }


    @GetMapping("/menu/deleted")
    public ElAdminResultBeans deletedMenu(Integer id){
        ElAdminResultBeans beans =  menuService.deletedMenu(id);
        return  beans;
    }

    @GetMapping("/role/menu")
    public  ElAdminResultBeans roleMenuList(Integer roleId){
        ElAdminResultBeans beans =  roleService.roleMenuList(roleId);
        return  beans;
    }


}