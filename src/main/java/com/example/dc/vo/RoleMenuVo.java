package com.example.dc.vo;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 14:49 2020/10/27
 * @ Description：
 */
@Data
public class RoleMenuVo {

    private  Integer roleId;

    private List<Integer> menuIds;
}