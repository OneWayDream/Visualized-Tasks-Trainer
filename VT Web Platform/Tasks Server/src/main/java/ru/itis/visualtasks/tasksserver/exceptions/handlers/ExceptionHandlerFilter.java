package ru.itis.visualtasks.tasksserver.exceptions.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request,
                                 @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex);
            response.setStatus(errorResponse.getStatus());
            response.setContentType("application/json");
            if (errorResponse.getMessage() != null){
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(errorResponse.getMessage()));
            }
        }
    }
}
