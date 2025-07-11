package com.EduHubAcademy.adminService.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
    public class FeignClientInterceptor implements RequestInterceptor {

        @Autowired
        private HttpServletRequest request;

        @Override
        public void apply(RequestTemplate template) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                template.header("Authorization", authHeader);
            }
        }


}
