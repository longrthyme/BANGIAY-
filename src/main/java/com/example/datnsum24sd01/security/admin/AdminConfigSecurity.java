package com.example.datnsum24sd01.security.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AdminConfigSecurity {
    //thông tin chi tiết của người dùng
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomNhanVienDetailService();
    }
//mã hóa pass
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
//dùng userdtail.. +pass để xác thực
    @Bean
    public DaoAuthenticationProvider providerAdmin() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }
//xử lí người dùng kh có quyền truy cập điều hướng login hoặc thông báo lỗi
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Bean
    public SecurityFilterChain filterChainAdmin(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                //được truy cập vào mà kh cần xác thực
                .authorizeHttpRequests(
                        rq ->
                                rq.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                        .requestMatchers("/**").permitAll()
                )
//login bằng emal ,pas  đăng nhập login ,chuyển hướng  url default
                .formLogin(
                        f -> f.loginPage("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/default")
                )
                //đăng xuất-> login
                .logout(
                        lo-> lo.logoutUrl("/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )

                .authenticationProvider(providerAdmin())
                .exceptionHandling(
                        ex -> ex.accessDeniedHandler(accessDeniedHandler())
                )
        ;
        return http.build();
    }

}
