package com.example.dc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:27 2020/10/19
 * @ Description：
 */

@Data
public class TestVo {

    private  String id;

    private String  rolesList;

    @JsonIgnore
    private  String showRoles;


    public String getRoles() {
        return this.showRoles;
    }
}