package com.example.dc.utils;

import com.example.dc.entity.user.UserEntity;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Author     ：duanchao
 * @ Date       ： 15:02 2020/9/25
 * @ Description：
 */
public class JwtUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

//    public static final String SUBJECT = "congge";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

//    public static final long EXPIRITION = 30000;

    public static final String APPSECRET_KEY = "congge_secret";

    private static final String ROLE_CLAIMS = "rol";


    public static String createToken(Integer id,String userName, List<String> roleList) {

        String auth = roleList.stream().collect(Collectors.joining(","));
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roleList);
        String token = Jwts
                .builder()
                .setSubject(userName)
                .setClaims(map)
                .claim("userName",userName)
                .claim("userId",id)
                .claim("auth",auth)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRITION))
                .signWith(SignatureAlgorithm.HS256,APPSECRET_KEY).compact();

        return  TOKEN_PREFIX + token;

    }

    public  static Claims checkJwt(String token){
        try {
            final Claims body = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return body;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (UnsupportedJwtException e) {
            throw e;
        } catch (MalformedJwtException e) {
            throw e;
        } catch (SignatureException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
    }

    public static boolean isExpiration(String token){
        Claims claims = checkJwt(token);

        return claims.getExpiration().before(new Date());
    }

    public  static  String getUserName(String token){
        Claims claims = checkJwt(token);
        String userName = claims.get("userName", String.class);
        return  userName;
    }

    public static  Integer getUserId (String token){
        Claims claims = checkJwt(token);
        Integer userId = claims.get("userId", Integer.class);
        return  userId;
    }


    public static  String getToken(HttpServletRequest request){
        final String requestHeader = request.getHeader(JwtUtils.TOKEN_HEADER);
        if(StringUtils.isBlank(requestHeader)){
            return "";
        }

        if(!requestHeader.startsWith("Bearer ")){
            return "";
        }
        return requestHeader.substring(7);

    }



}