package com.example.dc.service;

import com.example.dc.entity.user.UserEntity;
import com.example.dc.utils.ElAdminResultBeans;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:47 2020/9/25
 * @ Description：
 */
public interface UserService {

    ElAdminResultBeans login(UserEntity user);

    ElAdminResultBeans save(UserEntity user);

}
