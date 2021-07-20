package com.ruslanshakirov.crm.config.security;

import com.ruslanshakirov.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final String BEARER_VALUE = "Bearer";
    private final UserService userService;
    private final JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional.ofNullable(jwtHeader)
                .filter(h -> h.startsWith(BEARER_VALUE))
                .map(this::getAuthentication)
                .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(String jwtHeader) {
        String token = jwtHeader.replace(BEARER_VALUE, "").trim();
        if (!jwtUtils.validateJwtToken(token)) return null;
        try {
            UserDetails userDetails = userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token));
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        } catch (Exception e) {
            log.error("Cannot set authentication", e);
            return null;
        }
    }
}
