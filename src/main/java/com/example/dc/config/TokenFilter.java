package com.example.dc.config;

import com.alibaba.fastjson.JSONObject;
import com.example.dc.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


/**
 * @ Author     ：duanchao
 * @ Date       ： 14:56 2020/9/28
 * @ Description：
 */
@Component
public class TokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        JSONObject json = new JSONObject();
        String token = JwtUtils.getToken(request);
        if(StringUtils.isBlank(token)){
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token,request,response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
        } catch (ExpiredJwtException e) {
            json.put("codeCheck", false);
            json.put("msg", "Token已过期");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(json));
            logger.error("Token已过期: {} " + e);
        } catch (UnsupportedJwtException e) {
            //json.put("status", "-3");
            json.put("codeCheck", false);
            json.put("msg", "Token格式错误");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(json));
            logger.error("Token格式错误: {} " + e);
        } catch (MalformedJwtException e) {
            json.put("codeCheck", false);
            json.put("msg", "Token没有被正确构造");
             response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write(JSON.toJSONString(json));
            logger.error("Token没有被正确构造: {} " + e);
        } catch (SignatureException e) {
            json.put("codeCheck", false);
            json.put("msg", "Token签名失败");
             response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write(JSON.toJSONString(json));
            logger.error("签名失败: {} " + e);
        } catch (IllegalArgumentException e) {
            json.put("codeCheck", false);
            json.put("msg", "Token非法参数异常");
             response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write(JSON.toJSONString(json));
            logger.error("非法参数异常: {} " + e);
        }catch (Exception e){
            json.put("codeCheck", false);
            json.put("msg", "Invalid Token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(json));
            logger.error("Invalid Token " + e.getMessage());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token ,HttpServletRequest request, HttpServletResponse response) {

        if (token != null) {
            String userName="";

            try {
                // 解密Token
                Claims claims = JwtUtils.checkJwt(token);
                 userName = (String)claims.get("userName");
                 String auth = (String)claims.get("auth");
                Collection<? extends GrantedAuthority> authorities= StringUtils.isNotBlank(auth)?Arrays.stream(auth.split(",")).map(SimpleGrantedAuthority::new)
                         .collect(Collectors.toList()): Collections.emptyList();
                if (StringUtils.isNotBlank(userName)) {
                    User principal = new User(userName, "******", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, null,authorities);
                }
            }catch (ExpiredJwtException e) {
                throw e;
                //throw new TokenException("Token已过期");
            } catch (UnsupportedJwtException e) {
                throw e;
                //throw new TokenException("Token格式错误");
            } catch (MalformedJwtException e) {
                throw e;
                //throw new TokenException("Token没有被正确构造");
            } catch (SignatureException e) {
                throw e;
                //throw new TokenException("签名失败");
            } catch (IllegalArgumentException e) {
                throw e;
                //throw new TokenException("非法参数异常");
            }catch (Exception e){
                throw e;
                //throw new IllegalStateException("Invalid Token. "+e.getMessage());
            }
            return null;
        }
        return null;

    }




}