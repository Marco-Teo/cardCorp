package it.epicode.cardcorp.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwFilter extends OncePerRequestFilter {

    private final JwTool jwTool;
    public JwFilter(JwTool jwTool) {
        this.jwTool = jwTool;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(req, res);
            return;
        }

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                jwTool.validateToken(token);
                Claims claims = jwTool.getClaims(token);
                String role = claims.get("ruolo", String.class);
                int userId = Integer.parseInt(claims.getSubject());

                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                var auth = new UsernamePasswordAuthenticationToken(
                        userId, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (RuntimeException ex) {
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(req, res);
    }
}
