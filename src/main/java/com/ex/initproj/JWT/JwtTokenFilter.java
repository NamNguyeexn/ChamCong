package com.ex.initproj.JWT;

import com.ex.initproj.repositories.CustomUserRepository;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.Optional.ofNullable;
import static org.aspectj.util.LangUtil.isEmpty;
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter implements Filter {
    private final JwtTokenService jwtTokenService;

    private final CustomUserRepository customUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("==================================");
        log.info("JWT TOKEN FILTER - DO FILTER INTERNAL");
        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            log.info("-----------------------------------------------");
            log.info("JWT TOKEN FILTER - DO FILTER INTERNAL - WRONG HEADER");
            chain.doFilter(request, response);
            return;
        }
        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenService.validate(token)) {
            log.info("-----------------------------------------------");
            log.info("JWT TOKEN FILTER - DO FILTER INTERNAL - INVALIDATE");
            chain.doFilter(request, response);
            return;
        }
        // Get user identity and set it on the spring security context
        UserDetails userDetails = customUserRepository
                .getUserByUsername(jwtTokenService.getUsername(token))
                .orElse(null);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(new ArrayList<>())
        );
        authentication
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        chain.doFilter(request, response);
    }
}