package com.mir00r.userlogin.configuration;

import com.mir00r.userlogin.Utils.PasswordUtil;
import com.mir00r.userlogin.listeners.AuthenticationFailureEventListener;
import com.mir00r.userlogin.listeners.AuthenticationSuccessEventListener;
import com.mir00r.userlogin.users.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final AuthenticationSuccessEventListener successEventListener;
    private final AuthenticationFailureEventListener failureEventListener;
    //private final ActivityInterceptor activityInterceptor;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AppConfig(AuthenticationSuccessEventListener successEventListener, AuthenticationFailureEventListener failureEventListener,
                     //ActivityInterceptor activityInterceptor,
                     CustomUserDetailsService customUserDetailsService) {
        this.successEventListener = successEventListener;
        this.failureEventListener = failureEventListener;
        //this.activityInterceptor = activityInterceptor;
        this.userDetailsService = customUserDetailsService;
    }


    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(PasswordUtil.getBCryptPasswordEncoder());
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setDispatchOptionsRequest(true);
        return dispatcherServlet;
    }


    @Bean
    public ApplicationListener loginSuccessListener() {
        return this.successEventListener;
    }

    @Bean
    public ApplicationListener loginFailureListener() {
        return this.failureEventListener;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(activityInterceptor);
    }
}
