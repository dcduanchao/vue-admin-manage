package com.example.dc.config;

import com.example.dc.entity.user.UserEntity;
import com.example.dc.utils.JwtUtils;
import com.example.dc.vo.JwtUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @ Author     ：duanchao  成功失败后逻辑
 * @ Date       ： 17:25 2020/9/25
 * @ Description：去进行用户账号的验证
 */

/**
 *  只需创建一个token并调用authenticationManager.authenticate()
 *
 */
public class JWTAuthenticationFilter1 extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter1(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        //登录走auth/login
//        super.setFilterProcessesUrl("/auth/login1");
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {
            UserEntity userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            return  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getUserName(),userEntity.getPwd()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }



    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, 
                                            Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        String token = JwtUtils.createToken(jwtUser.getId(), jwtUser.getUsername(), Collections.emptyList());
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.setHeader("token",JwtUtils.TOKEN_PREFIX +token);


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                              AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}