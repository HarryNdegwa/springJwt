package com.example.spring_jwt.demo.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.spring_jwt.demo.security.services.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.util.StringUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // final String requestTokenHeader = request.getHeader("Authorization");

        String token = parseJwt(request);

        // if (StringUtils.hasText(requestTokenHeader) &&
        // requestTokenHeader.startsWith("Bearer ")) {
        // return requestTokenHeader.substring(7, requestTokenHeader.length());
        // }

        // if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

        // token = requestTokenHeader.substring(7);

        // try {
        // username = jwtTokenUtil.getUsernameFromToken(token);
        // } catch (IllegalArgumentException e) {
        // System.out.println(e);
        // System.out.println("Unable to get JWT token");
        // } catch (ExpiredJwtException e) {
        // System.out.println(e);
        // System.out.println("Token expired!");
        // }
        // }

        if (token != null) {
            String username = jwtTokenUtil.getUsernameFromToken(token);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (token != null && jwtTokenUtil.validateToken(token, userDetails)) {

                // if (jwtTokenUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                        userDetails.getPassword());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        chain.doFilter(request, response);

    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

}
