package com.example.dc.service.impl;

import com.example.dc.dao.UserRepository;
import com.example.dc.dao.UserRoleRepository;
import com.example.dc.entity.user.UserEntity;
import com.example.dc.entity.user.UserRoleEntity;
import com.example.dc.service.UserService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.JwtUtils;
import com.example.dc.utils.ResponseUtils;
import com.example.dc.vo.user.UserBaseVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:47 2020/9/25
 * @ Description：
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ElAdminResultBeans login(UserEntity user) {

        UserEntity userEntity = userRepository.findByUserName(user.getUserName());
        String token = JwtUtils.createToken(userEntity.getId(), userEntity.getUserName(), Collections.emptyList());
        return ResponseUtils.success(token);
    }

    @Override
    public ElAdminResultBeans save(UserEntity user) {
        UserEntity save = userRepository.save(user);

        return ResponseUtils.success(save);
    }

    @Override
    public ElAdminResultBeans userList(String userName) {

        List<UserEntity> list = new ArrayList<>();
        if (StringUtils.isNotBlank(userName)) {
            list = userRepository.findByUserNameLike("%" + userName + "%");
        } else {
            list =  userRepository.findAll();
        }
        List<Integer> userIds = list.stream().map(UserEntity::getId).collect(Collectors.toList());
        List<UserBaseVo> userBaseVolist = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(userIds)){
            List<UserRoleEntity> byUserIdIn = userRoleRepository.findByUserIdIn(userIds);
            Map<Integer, List<Integer>> userRoleMap = byUserIdIn.stream().collect(Collectors.groupingBy(UserRoleEntity::getUserId,
                    Collectors.mapping(UserRoleEntity::getRoleId, Collectors.toList())));

            for (UserEntity ue : list) {
                UserBaseVo vo = new UserBaseVo();
                BeanUtils.copyProperties(ue,vo);
                List<Integer> roleIds = userRoleMap.get(ue.getId());
                vo.setRoleIds(roleIds);
                userBaseVolist.add(vo);
            }

        }

        return ResponseUtils.success(userBaseVolist);
    }

    @Transactional
    @Override
    public ElAdminResultBeans addOrEdit(UserBaseVo userBaseVo) {

        UserEntity userEntity = new UserEntity();

        if(userBaseVo.getId()==null){
            //默认密码123456
            String pwd = bCryptPasswordEncoder.encode("123456");
            userEntity.setPwd(pwd);
        }else {
            userEntity =  userRepository.findById(userBaseVo.getId()).get();
        }
        BeanUtils.copyProperties(userBaseVo,userEntity);
        UserEntity save = userRepository.save(userEntity);
        userRoleRepository.deleteByUserId(save.getId());

        List<Integer> roleIds = userBaseVo.getRoleIds();
        if(CollectionUtils.isNotEmpty(roleIds)){
            List<UserRoleEntity> userRoleEntities = new ArrayList<>();
            for (Integer roleId : roleIds) {
                UserRoleEntity entity = new UserRoleEntity();

                entity.setUserId(save.getId());
                entity.setRoleId(roleId);
                userRoleEntities.add(entity);

            }
            userRoleRepository.saveAll(userRoleEntities);
        }

        return ResponseUtils.success();
    }


    @Transactional
    @Override
    public ElAdminResultBeans deleted(Integer id) {
        userRepository.deleteById(id);
        userRoleRepository.deleteByUserId(id);
        return  ResponseUtils.success();
    }
}