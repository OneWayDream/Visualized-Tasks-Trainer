package ru.itis.visualtasks.jwtserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.visualtasks.jwtserver.exceptions.token.IncorrectJwtException;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUtils {

    private final List<String> secretKeys;

    @Autowired
    public JwtUtils(
            @Value("${jwt.module.refresh-token.secret-key}") String moduleRefreshSecretKey,
            @Value("${jwt.module.access-token.secret-key}") String moduleAccessSecretKey,
            @Value("${jwt.user.refresh-token.secret-key}") String userRefreshSecretKey,
            @Value("${jwt.user.access-token.secret-key}") String userAccessSecretKey
    ){
        secretKeys = new ArrayList<>();
        secretKeys.add(moduleRefreshSecretKey);
        secretKeys.add(moduleAccessSecretKey);
        secretKeys.add(userRefreshSecretKey);
        secretKeys.add(userAccessSecretKey);
    }

    public DecodedJWT decodeJwt(String token){
        for (String secretKey : secretKeys){
            try{
                return JWT.require(Algorithm.HMAC256(secretKey))
                        .build()
                        .verify(token);
            } catch (JWTVerificationException ex){
                //wrong key :c
            }
        }
        throw new IncorrectJwtException();
    }

}
