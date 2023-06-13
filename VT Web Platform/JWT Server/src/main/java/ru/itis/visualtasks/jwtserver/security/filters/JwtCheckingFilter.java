package ru.itis.visualtasks.jwtserver.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.visualtasks.jwtserver.exceptions.token.BannedTokenException;
import ru.itis.visualtasks.jwtserver.exceptions.token.ExpiredJwtException;
import ru.itis.visualtasks.jwtserver.services.JwtBlacklistService;
import ru.itis.visualtasks.jwtserver.utils.JwtUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtCheckingFilter extends OncePerRequestFilter {

    private final JwtBlacklistService service;
    private final JwtUtils jwtUtils;

    @Autowired
    public JwtCheckingFilter(
            JwtBlacklistService service,
            JwtUtils jwtUtils
    ){
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        List<String> tokens = new ArrayList<>();
        if (request.getHeader("JWT") != null){
            tokens.add(request.getHeader("JWT"));
        }
        if (request.getHeader("refresh-token") != null){
            tokens.add(request.getHeader("refresh-token"));
        }
        for (String token : tokens){
            if (service.exists(token)) {
                throw new BannedTokenException();
            }

            DecodedJWT decodedJWT = jwtUtils.decodeJwt(token);

            String tokenExpirationDateString = decodedJWT.getClaim("expiration").asString();
            if (tokenExpirationDateString != null){
                LocalDateTime tokenExpirationDate = LocalDateTime.parse(tokenExpirationDateString);
                LocalDateTime date = LocalDateTime.now();
                if (!date.isBefore(tokenExpirationDate)){
                    throw new ExpiredJwtException();
                }
            }
        }
        chain.doFilter(request, response);
    }


}
