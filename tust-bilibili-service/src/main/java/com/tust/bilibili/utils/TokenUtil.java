package com.tust.bilibili.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tust.bilibili.domain.exception.CustomException;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * JWT生成token
 */
public class TokenUtil {

    private static final String ISSUER = "Bukayo_2020_4_26";

    /**
     * 生成token
     */
    public static String generateToken(Long userId) throws Exception{
        // 1. 使用RSA加密算法对token进行加密
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        // 2. 给token设置存活时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);  // 先添加一个30min的过期时间
        // 3. 生成token
        return JWT.create().withKeyId(String.valueOf(userId))
                            .withIssuer(ISSUER)
                            .withExpiresAt(calendar.getTime())
                            .sign(algorithm);
    }

    /**
     * 验证token
     */
    public static Long verifyToken(String token) {
        try {
            // 1. 设置token使用的加密算法，用来解密token
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            // 2. 验证token
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            // 3. 返回用户id
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException e){
            throw new CustomException("501","token已经过期");
        } catch (Exception e) {
            throw new CustomException("用户token非法");
        }

    }

}
