package com.example.dc.controller;

import com.example.dc.entity.user.UserEntity;
import com.example.dc.from.UserListFrom;
import com.example.dc.service.UserService;
import com.example.dc.utils.ElAdminResultBeans;
import com.example.dc.utils.JwtUtils;
import com.example.dc.utils.ResponseUtils;
import com.example.dc.vo.JwtUser;
import com.example.dc.vo.JwtUserDto;
import com.example.dc.vo.user.UserBaseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:58 2020/7/20
 * @ Description：
 */
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired(required=true)
    private   AuthenticationManager authenticationManager;

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/auth/login")
    public ElAdminResultBeans login(@RequestBody UserEntity user) {
        String pwd = user.getPwd();
        //不需要加密  前端要加密后需解密
//        String encode = bCryptPasswordEncoder.encode(pwd);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), pwd);
        //1
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //设置只上下文
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        JwtUser principal = (JwtUser) authenticate.getPrincipal();
        String token = JwtUtils.createToken(principal.getId(), principal.getUsername(),principal.getRoleList() );

        JwtUserDto jwtUserDto = new JwtUserDto();
        jwtUserDto.setJwtUser(principal);
        jwtUserDto.setToken(token);

        return ResponseUtils.success(jwtUserDto);
    }


    @PostMapping("/register")
    public ElAdminResultBeans registerUser(@RequestBody UserEntity user) {
        String pwd = user.getPwd();

        // 记得注册的时候把密码加密一下
        String encode = bCryptPasswordEncoder.encode(pwd);
        user.setPwd(encode);
        ElAdminResultBeans bean = userService.save(user);

        return bean;
    }


    @GetMapping("/user/list")
    public ElAdminResultBeans userList(String userName){
        ElAdminResultBeans bean =  userService.userList(userName);
        return bean;
    }
    @PostMapping("/user/addoredit")
    public ElAdminResultBeans addOrEdit(@RequestBody UserBaseVo userBaseVo){
        ElAdminResultBeans bean =  userService.addOrEdit(userBaseVo);
        return bean;
    }

    @GetMapping("/user/deleted")
    public ElAdminResultBeans deleted(Integer id){
        ElAdminResultBeans bean =  userService.deleted(id);
        return bean;
    }


    @PostMapping("/user/page/list")
    public ElAdminResultBeans userPageList(@RequestBody UserListFrom userListFrom){
        ElAdminResultBeans bean =  userService.userPageList(userListFrom);
        return bean;
    }

}