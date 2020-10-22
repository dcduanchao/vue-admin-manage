package com.example.dc.service.impl;

import com.example.dc.dao.UserRepository;
import com.example.dc.entity.user.UserEntity;
import com.example.dc.service.UserService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.JwtUtils;
import com.example.dc.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:47 2020/9/25
 * @ Description：
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private  final UserRepository userRepository;
    @Override
    public ElAdminResultBeans login(UserEntity user) {

        UserEntity userEntity = userRepository.findByUserName(user.getUserName());
        String token = JwtUtils.createToken(userEntity.getId(),userEntity.getUserName(), Collections.emptyList());
        return ResponseUtils.success(token);
    }

    @Override
    public ElAdminResultBeans save(UserEntity user) {
        UserEntity save = userRepository.save(user);

        return ResponseUtils.success(save);
    }
}