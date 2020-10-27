package com.example.dc.service.impl;

import com.example.dc.dao.MenuRepository;
import com.example.dc.entity.user.MenuEntity;
import com.example.dc.service.MenuService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.ResponseUtils;
import com.example.dc.vo.menu.MenuTreeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:09 2020/9/27
 * @ Description：
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuRepository menuRepository;

    @Override
    public ElAdminResultBeans getMenuTree(String menuName) {
        List<MenuTreeVo> menuTreeVo = new ArrayList<>();
        List<MenuEntity> menuList = new ArrayList<>();
        if(StringUtils.isNotBlank(menuName)){
            menuList = menuRepository.findByNameLikeOrderBySortAsc("%" + menuName + "%");
        }else {
            menuList = menuRepository.findAll(Sort.by(Sort.Direction.ASC,"sort"));
        }
        if(CollectionUtils.isNotEmpty(menuList)){
            Integer pid = menuList.stream().min(Comparator.comparingInt(MenuEntity::getPid)).get().getPid();
            //有筛选报错
//            menuTreeVo = buildMenuTree(menuList,pid);

            menuTreeVo = buildByFor(menuList);
        }
        return ResponseUtils.success(menuTreeVo);
    }

    @Override
    public ElAdminResultBeans getHomeMenuTree() {

        List<MenuEntity> menuList   = menuRepository.findByTypeInOrderBySortAsc(Arrays.asList(1,2));
        List<MenuTreeVo> menuTreeVo = buildByFor(menuList);

        return ResponseUtils.success(menuTreeVo);
    }



    private List<MenuTreeVo> buildByFor(List<MenuEntity> menuList) {
        List<MenuTreeVo> menuTreeVos = new ArrayList<>();
        for (MenuEntity menuEntity : menuList) {
            MenuTreeVo menuTreeVo = new MenuTreeVo();
            BeanUtils.copyProperties(menuEntity,menuTreeVo);
            menuTreeVos.add(menuTreeVo);
        }
        Set<Integer> ids = new HashSet<>();
        List<MenuTreeVo> trees = new ArrayList<>();
        for (MenuTreeVo me : menuTreeVos) {
            Integer pid = me.getPid();
            if(pid==0){
                trees.add(me);
            }
            for (MenuTreeVo m : menuTreeVos) {
                if(m.getPid().equals(me.getId())){
                    if(CollectionUtils.isEmpty(me.getChildren())){
                        me.setChildren(new ArrayList<>());
                    }
                    me.getChildren().add(m);
                    ids.add(m.getId());
                }

            }

        }
        if(trees.size() == 0){
            trees = menuTreeVos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return  trees;
    }

    private List<MenuTreeVo>  buildMenuTree(List<MenuEntity> menuList,int pid) {
        List<MenuTreeVo> menuTreeVos = new ArrayList<>();
        List<MenuEntity> pidList = menuList.stream()
                .filter(p -> p.getPid() == pid)
                .sorted(Comparator.comparingInt(MenuEntity::getSort)).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(pidList)){
            return  null;
        }
        for (MenuEntity menuEntity : pidList) {
            MenuTreeVo menuTreeVo = new MenuTreeVo();
            BeanUtils.copyProperties(menuEntity, menuTreeVo);
            List<MenuTreeVo> child = buildMenuTree(menuList, menuEntity.getId());
            if (CollectionUtils.isNotEmpty(child)) {
                menuTreeVo.setChildren(child);
            }
            menuTreeVos.add(menuTreeVo);
        }

        return menuTreeVos;
    }




    @Override
    public ElAdminResultBeans saveOrEdit(MenuEntity menuEntity) {

        menuRepository.save(menuEntity);
        return ResponseUtils.success();
    }

    @Override
    public ElAdminResultBeans deletedMenu(Integer id) {
        menuRepository.deleteById(id);
        return ResponseUtils.success();
    }



}