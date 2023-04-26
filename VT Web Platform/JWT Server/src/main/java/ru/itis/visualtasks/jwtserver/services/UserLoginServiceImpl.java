package ru.itis.visualtasks.jwtserver.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.JwtUserDto;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.UserAuthorizationForm;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.exceptions.auth.BannedUserException;
import ru.itis.visualtasks.jwtserver.exceptions.auth.IncorrectUserDataException;
import ru.itis.visualtasks.jwtserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.jwtserver.exceptions.token.BannedTokenException;
import ru.itis.visualtasks.jwtserver.exceptions.token.IncorrectJwtException;
import ru.itis.visualtasks.jwtserver.redis.services.RedisUsersService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    protected final JwtUserService service;
    protected final PasswordEncoder passwordEncoder;
    protected final RedisUsersService redisUsersService;
    protected final JwtBlacklistService jwtBlacklistService;

    protected String refreshSecretKey;
    protected String accessSecretKey;
    protected Long refreshTokenLifetime;
    protected Long accessTokenLifetime;

    @Autowired
    public UserLoginServiceImpl(
            JwtUserService service,
            PasswordEncoder passwordEncoder,
            RedisUsersService redisUsersService,
            JwtBlacklistService jwtBlacklistService,
            @Value("${jwt.user.refresh-token.secret-key}") String refreshSecretKey,
            @Value("${jwt.user.access-token.secret-key}") String accessSecretKey,
            @Value("${jwt.user.refresh-token.lifetime}") Long refreshTokenLifetime,
            @Value("${jwt.user.access-token.lifetime}") Long accessTokenLifetime
    ){
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.redisUsersService = redisUsersService;
        this.jwtBlacklistService = jwtBlacklistService;
        this.refreshTokenLifetime = refreshTokenLifetime;
        this.accessTokenLifetime = accessTokenLifetime;
        this.accessSecretKey = accessSecretKey;
        this.refreshSecretKey = refreshSecretKey;
    }

    @Override
    public RefreshTokenResponse login(UserAuthorizationForm form) {
        JwtUserDto user;
        try{
            if (form.getLogin() != null){
                user = service.findByLogin(form.getLogin());
            } else {
                user = service.findByMail(form.getMail());
            }
        } catch (EntityNotFoundException ex){
            throw new IncorrectUserDataException(ex);
        }
        if (user.getState().equals(JwtState.BANNED)){
            throw new BannedUserException();
        }
        if (passwordEncoder.matches(form.getPassword(), user.getHashPassword())){
            LocalDateTime date = null;
            if (refreshTokenLifetime > 0){
                date = LocalDateTime.now().plus(refreshTokenLifetime, ChronoUnit.MILLIS);
            }
            String token = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("account_id", user.getAccountId())
                    .withClaim("login", user.getLogin())
                    .withClaim("state", user.getState().toString())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("expiration", date == null ? null : date.toString())
                    .withClaim("redis_id", user.getRedisId())
                    .withClaim("salt", UUID.randomUUID().toString())
                    .sign(Algorithm.HMAC256(refreshSecretKey));
            redisUsersService.addTokenToUser(user, token);
            return RefreshTokenResponse.builder()
                    .token(token)
                    .expiredTime(date)
                    .build();
        } else {
            throw new IncorrectUserDataException();
        }
    }

    @Override
    public AccessTokenResponse authenticate(RefreshTokenResponse refreshTokenDto) {
        if (jwtBlacklistService.exists(refreshTokenDto.getToken())){
            throw new BannedTokenException();
        }
        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(refreshSecretKey))
                    .build()
                    .verify(refreshTokenDto.getToken());
            LocalDateTime date = null;
            if (accessTokenLifetime > 0){
                date = LocalDateTime.now().plus(accessTokenLifetime, ChronoUnit.MILLIS);
            }
            String accessToken = JWT.create()
                    .withSubject(decodedJWT.getSubject())
                    .withClaim("account_id", decodedJWT.getClaim("account_id").asLong())
                    .withClaim("login", decodedJWT.getClaim("login").asString())
                    .withClaim("role", decodedJWT.getClaim("role").asString())
                    .withClaim("state", decodedJWT.getClaim("state").asString())
                    .withClaim("expiration", date == null ? null : date.toString())
                    .withClaim("salt", UUID.randomUUID().toString())
                    .sign(Algorithm.HMAC256(accessSecretKey));
            redisUsersService.addTokenToUser(JwtUserDto.builder()
                            .id(Long.valueOf(decodedJWT.getSubject()))
                            .redisId(decodedJWT.getClaim("redis_id").asString())
                            .build(), accessToken);
            return AccessTokenResponse.builder()
                    .token(accessToken)
                    .expiredTime(date)
                    .build();
        } catch (JWTVerificationException ex) {
            throw new IncorrectJwtException(ex);
        }
    }

}
