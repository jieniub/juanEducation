package com.ljj.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//jwt工具类
public class JwtUtils {
    private static long EXPIRE = 1000 * 60 * 60 *24;
    private static String APP_SECRET = "lvjunjiezuisuainskdlFDC83kbKHD";

    public static String getJwtToken(String id,String nickname){
       String JwtString = Jwts.builder()
               .setHeaderParam("typ","jwt")
               .setHeaderParam("alg","HS256")
               .setSubject("juan-user")
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
               .claim("id",id)
               .claim("nikename",nickname)
               .signWith(SignatureAlgorithm.HS256,APP_SECRET)
               .compact();
       return JwtString;
    }

    public static boolean checkToken(String jwtToken){
        if(StringUtils.isEmpty(jwtToken)) return false;
        try{
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean checkToken(HttpServletRequest request){
        try{
            String jwtToken = request.getHeader("token");
            if (StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getMemberIdByJwtToken(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

}
