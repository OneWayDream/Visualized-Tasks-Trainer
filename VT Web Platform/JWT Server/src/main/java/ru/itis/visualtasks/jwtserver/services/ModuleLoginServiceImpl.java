package ru.itis.visualtasks.jwtserver.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.jwtserver.dto.AccessTokenResponse;
import ru.itis.visualtasks.jwtserver.dto.JwtModuleDto;
import ru.itis.visualtasks.jwtserver.dto.ModuleAuthorizationForm;
import ru.itis.visualtasks.jwtserver.dto.RefreshTokenResponse;
import ru.itis.visualtasks.jwtserver.entities.JwtState;
import ru.itis.visualtasks.jwtserver.exceptions.auth.BannedUserException;
import ru.itis.visualtasks.jwtserver.exceptions.auth.IncorrectUserDataException;
import ru.itis.visualtasks.jwtserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.jwtserver.exceptions.token.IncorrectJwtException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ModuleLoginServiceImpl implements ModuleLoginService {

    protected final JwtModuleService service;
    protected final PasswordEncoder passwordEncoder;

    protected String refreshSecretKey;
    protected String accessSecretKey;
    protected Long refreshTokenLifetime;
    protected Long accessTokenLifetime;

    @Autowired
    public ModuleLoginServiceImpl(
            JwtModuleService service,
            PasswordEncoder passwordEncoder,
            @Value("${jwt.module.refresh-token.secret-key}") String refreshSecretKey,
            @Value("${jwt.module.access-token.secret-key}") String accessSecretKey,
            @Value("${jwt.module.refresh-token.lifetime}") Long refreshTokenLifetime,
            @Value("${jwt.module.access-token.lifetime}") Long accessTokenLifetime
    ){
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.refreshSecretKey = refreshSecretKey;
        this.accessSecretKey = accessSecretKey;
        this.refreshTokenLifetime = refreshTokenLifetime;
        this.accessTokenLifetime = accessTokenLifetime;
    }

    @Override
    public RefreshTokenResponse login(ModuleAuthorizationForm form) {
        JwtModuleDto module;
        try{
            module = service.findByLogin(form.getLogin());
        } catch (EntityNotFoundException ex){
            throw new IncorrectUserDataException(ex);
        }
        if (module.getState().equals(JwtState.BANNED)){
            throw new BannedUserException();
        }
        if (passwordEncoder.matches(form.getPassword(), module.getHashPassword())){
            LocalDateTime date = null;
            if (refreshTokenLifetime > 0){
                date = LocalDateTime.now().plus(refreshTokenLifetime, ChronoUnit.MILLIS);
            }
            String token = JWT.create()
                    .withSubject(module.getId().toString())
                    .withClaim("id", module.getId())
                    .withClaim("login", module.getLogin())
                    .withClaim("state", module.getState().toString())
                    .withClaim("role", module.getRole().toString())
                    .withClaim("expiration", date == null ? null : date.toString())
                    .sign(Algorithm.HMAC256(refreshSecretKey));
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
                    .withClaim("id", decodedJWT.getClaim("id").asLong())
                    .withClaim("login", decodedJWT.getClaim("login").asString())
                    .withClaim("role", decodedJWT.getClaim("role").asString())
                    .withClaim("state", decodedJWT.getClaim("state").asString())
                    .withClaim("expiration", date == null ? null : date.toString())
                    .sign(Algorithm.HMAC256(accessSecretKey));
            return AccessTokenResponse.builder()
                    .token(accessToken)
                    .expiredTime(date)
                    .build();
        } catch (JWTVerificationException ex) {
            throw new IncorrectJwtException(ex);
        }
    }

}
