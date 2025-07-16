package it.epicode.cardcorp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.cardcorp.exeption.NotFoundException;
import it.epicode.cardcorp.model.User;
import it.epicode.cardcorp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
    public class JwTool {

        @Autowired
        @Lazy
        private UserService userService;

        @Value("${jwt.duration}")
        private Long durata;

        @Value("${jwt.secret}")
        private String chiaveSegreta;


        public String createToken(User user) {
            return Jwts.builder()
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + durata))
                    .setSubject(String.valueOf(user.getId()))
                    .claim("username", user.getUserName())
                    .claim("ruolo", user.getRuolo() != null ? user.getRuolo().name() : "")
                    .signWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes()))
                    .compact();
        }


        public void validateToken(String token) {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes()))
                    .build()
                    .parseClaimsJws(token);
        }


        public User getUserFromToken(String token) throws NotFoundException {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            int id = Integer.parseInt(claims.getSubject());
            return userService.findById(id);
        }

        public Claims getClaims(String token) {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
    }


