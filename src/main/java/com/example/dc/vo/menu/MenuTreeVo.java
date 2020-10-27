package com.example.dc.vo.menu;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:02 2020/10/22
 * @ Description：
 */

@Data
public class MenuTreeVo {


    private Integer id;
    private String name;
    private String component;
    private Integer pid;
    private Integer sort;
    private String icon;
    private String path;
    private Integer hidden;
    private String componentName;
    private String permission;
    private Integer type;

    private List<MenuTreeVo> children;

}