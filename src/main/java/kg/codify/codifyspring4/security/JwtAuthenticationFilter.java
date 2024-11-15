package kg.codify.codifyspring4.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.codify.codifyspring4.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtTokenHandler;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = this.parseJwt(request);
            if (Objects.isNull(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            if (this.jwtTokenHandler.validateToken(token)) {
                String username = this.jwtTokenHandler.getUsernameFromToken(token);
                UserDetails user = this.userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken =
                        UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String parseJwt(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (Objects.isNull(authHeader)) {
            return null;
        }
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new AuthenticationException("NO JWT");
    }
}
