package com.example.assignment167.config;

import com.google.gson.Gson;
import com.example.assignment167.entiy.dto.CredentialDto;
import com.example.assignment167.entiy.dto.LoginDto;
import com.example.assignment167.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private  final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("checking login information");
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            LoginDto loginDto = gson.fromJson(jsonData,LoginDto.class);
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            return null;
        }

    }
    // xu ly dang nhap thanh cong
    // tra ve access token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();// get user that successfully login
        //generate tokens
        String accessToken = JwtUtil.generateToken(user.getUsername(),
                user.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURL().toString(),
                JwtUtil.ONE_DAY + 7);
        //generate refresh token
        String refreshToken = JwtUtil.generateToken(user.getUsername(),
                user.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURL().toString(),
                JwtUtil.ONE_DAY + 14);
        CredentialDto credential  = new CredentialDto(accessToken,refreshToken);
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(credential));

    }
    // xu ly dang nhap ko thanh cong ,thong bao  loi , tra ve error dang json
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        HashMap<String,String> errors = new HashMap<>();
        errors.put("Message", "Invalid information");
        response.setContentType(MediaType.APPLICATION_ATOM_XML_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(errors));
    }
}
