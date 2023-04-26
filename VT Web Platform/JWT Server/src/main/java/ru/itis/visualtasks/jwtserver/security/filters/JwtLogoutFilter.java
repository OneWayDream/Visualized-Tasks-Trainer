package ru.itis.visualtasks.jwtserver.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.visualtasks.jwtserver.services.JwtBlacklistService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtLogoutFilter extends OncePerRequestFilter {

    private final JwtBlacklistService service;

    private final RequestMatcher logoutRequest = new AntPathRequestMatcher("/logout", "GET");
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            service.add(request.getHeader("JWT"));
            SecurityContextHolder.clearContext();
            return;
        }

        filterChain.doFilter(request, httpServletResponse);
    }
}
