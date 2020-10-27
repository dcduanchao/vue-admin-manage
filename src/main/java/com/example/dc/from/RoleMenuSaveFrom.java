package com.example.dc.from;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:18 2020/10/27
 * @ Description：
 */

@Data
public class RoleMenuSaveFrom {

    private  Integer roleId;

    private List<Integer> menuIds;
}