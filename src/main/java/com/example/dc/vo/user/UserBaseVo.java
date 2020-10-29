package com.example.dc.vo.user;

import lombok.Data;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:06 2020/10/28
 * @ Description：
 */
@Data
public class UserBaseVo {

    private Integer id;
    private String userName;
    private String nickName;
    private String phone;
    private Integer sex;
    private Integer age;
    private Integer userStatus;

    private List<Integer> roleIds;
}