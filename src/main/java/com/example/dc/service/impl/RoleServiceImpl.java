package com.example.dc.service.impl;

import com.example.dc.dao.MenuRepository;
import com.example.dc.dao.RoleMenuRepository;
import com.example.dc.dao.RoleRepository;
import com.example.dc.dao.UserRoleRepository;
import com.example.dc.entity.user.MenuEntity;
import com.example.dc.entity.user.RoleEntity;
import com.example.dc.entity.user.RoleMenuEntity;
import com.example.dc.entity.user.UserRoleEntity;
import com.example.dc.from.RoleMenuSaveFrom;
import com.example.dc.service.RoleService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.ResponseUtils;
import com.example.dc.vo.RoleMenuVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:09 2020/9/27
 * @ Description：
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleMenuRepository roleMenuRepository;
    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<String> getUserAuth(int userId, String userName) {
        List<String> auth = new ArrayList<>();


        if(userName.equals("admin")){
            auth.add("admin");
        }else {
            auth.add("test");
        }

        List<UserRoleEntity> byUserId = userRoleRepository.findByUserId(userId);
        if(CollectionUtils.isNotEmpty(byUserId)){
            List<Integer> roleIds = byUserId.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());

            List<RoleMenuEntity> roleMenu = roleMenuRepository.findByRoleIdIn(roleIds);
            if(CollectionUtils.isNotEmpty(roleMenu)){
                List<Integer> menuIds = roleMenu.stream().map(RoleMenuEntity::getMenuId).distinct().collect(Collectors.toList());
                List<MenuEntity> byIdIn = menuRepository.findByIdIn(menuIds);
                if(CollectionUtils.isNotEmpty(byIdIn)){
                    auth= byIdIn.stream().filter(p->StringUtils.isNotBlank(p.getPermission())).map(MenuEntity::getPermission).collect(Collectors.toList());
                }
            }
        }


        return auth;
    }

    @Override
    public ElAdminResultBeans getRoleList(String roleName) {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        if(StringUtils.isNotBlank(roleName)){
            roleEntityList= roleRepository.findByNameLike("%"+roleName+"%");
        }else {
            roleEntityList = roleRepository.findAll();
        }
        return ResponseUtils.success(roleEntityList);
    }

    @Override
    public ElAdminResultBeans saveOrEdit(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
        return ResponseUtils.success();
    }

    @Override
    public ElAdminResultBeans deletedRole(Integer id) {

        roleRepository.deleteById(id);
        return ResponseUtils.success();
    }

    @Override
    public ElAdminResultBeans roleMenuList(Integer roleId) {
        List<RoleMenuEntity> byRoleId = roleMenuRepository.findByRoleId(roleId);
        if(CollectionUtils.isEmpty(byRoleId)){
            return ResponseUtils.success(Collections.emptyList());
        }
        List<Integer> menuIds = byRoleId.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
        return ResponseUtils.success(menuIds);
    }

    @Override
    public ElAdminResultBeans allRoleMenuList() {
        List<RoleMenuEntity> all = roleMenuRepository.findAll();

        Map<Integer, List<Integer>> collect = all.stream().collect(Collectors.groupingBy(RoleMenuEntity::getRoleId,
                Collectors.mapping(RoleMenuEntity::getMenuId, Collectors.toList())));
//        List<RoleMenuVo> roleMenuVos = new ArrayList<>();
//        for (Integer roleId : collect.keySet()) {
//            RoleMenuVo vo = new RoleMenuVo();
//            vo.setRoleId(roleId);
//            vo.setMenuIds(collect.get(roleId));
//            roleMenuVos.add(vo);
//        }
        return ResponseUtils.success(collect);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ElAdminResultBeans saveRoleMenu(RoleMenuSaveFrom saveFrom) {
        Integer roleId = saveFrom.getRoleId();
        if(roleId==null){
            return ResponseUtils.success();
        }
        roleMenuRepository.deleteByRoleId(roleId);

        List<Integer> menuIds = saveFrom.getMenuIds();
        if(CollectionUtils.isNotEmpty(menuIds)){
            List<RoleMenuEntity> list = new ArrayList<>();
            menuIds.forEach(p->{
                RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                roleMenuEntity.setRoleId(roleId);
                roleMenuEntity.setMenuId(p);
                list.add(roleMenuEntity);

            });
            roleMenuRepository.saveAll(list);
        }

        return ResponseUtils.success();
    }
}