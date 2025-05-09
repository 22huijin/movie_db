//package com.example.demo.global.config;
//
//
//import com.example.demo.login.interceptor.AdminCheckInterceptor;
//import com.example.demo.login.interceptor.LoginCheckInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        // 로그인 필요 인터셉터
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .addPathPatterns("/api/**")
//                .excludePathPatterns(
//                        "/api/users/signup",
//                        "/api/users/login",
//                        "/api/movies/simple",
//                        "/api/movies/details/**",
//                        "/api/movies/search",
//                        "/api/movies/update-status-today",
//                        "/css/**", "/js/**", "/images/**"
//                );
//
//        // 관리자 전용 인터셉터
//        registry.addInterceptor(new AdminCheckInterceptor())
//                .addPathPatterns(
//                        "/api/admin/**",
//                        "/api/movies/create",
//                        "/api/schedules/register",
//                        "/api/schedules/delete",
//                        "/api/screens/**",
//                        "/api/coupons/issue/**"
//                );
//    }
//}
