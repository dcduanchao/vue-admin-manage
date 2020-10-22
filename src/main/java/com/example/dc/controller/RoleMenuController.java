package com.example.dc.controller;

import com.example.dc.entity.user.RoleEntity;
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


}