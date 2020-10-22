package com.example.dc.vo;

import com.example.dc.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:08 2020/9/25
 * @ Description：
 */
@Data
@NoArgsConstructor
public class JwtUser implements UserDetails {


    private  Integer id;

    private String userName;

    private Collection<? extends GrantedAuthority> authorities;

    private List<String> roleList;

    @JsonIgnore
    private String password;

    public JwtUser(UserEntity user, List<String> roleList) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPwd();
        this.roleList = roleList;
        authorities = roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}