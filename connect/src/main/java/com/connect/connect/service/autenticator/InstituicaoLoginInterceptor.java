package com.connect.connect.service.autenticator;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.connect.connect.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InstituicaoLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        

        if (CookieService.getCookie(request, "instituicaoId") != null) {
            return true; 
        }


        response.sendRedirect("/");
        return false;
    }
}