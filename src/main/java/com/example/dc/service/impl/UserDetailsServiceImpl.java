package com.example.dc.service.impl;

import com.example.dc.dao.UserRepository;
import com.example.dc.entity.user.UserEntity;
import com.example.dc.service.RoleService;
import com.example.dc.vo.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 16:59 2020/9/25
 * @ Description：
 */

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity byUserName = userRepository.findByUserName(userName);
        if(null==byUserName){
            throw  new UsernameNotFoundException("用户名不存在");

        }
        List<String> userAuth = roleService.getUserAuth(byUserName.getId(), byUserName.getUserName());

        return new JwtUser(byUserName,userAuth);
    }
}