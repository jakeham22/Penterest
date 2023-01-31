package com.example.LoginTest.config;

import com.example.LoginTest.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.LoginTest.config.jwt.JwtAuthenticationFilter;
import com.example.LoginTest.config.jwt.JwtAuthorizationFilter;



/**
 *
 * 로그인 로직용 Spring Security 관련 설정 코드
 * @EnableWebSecurity : Spring Security config를 할 클래스
 * PasswordEncoder : 입력받은 비밀번호를 그대로 DB에 저장하는 것이 아니고 암호화를 해서 저장
 * PasswordEncoder :따라서 이러한 암호화를 해주는 메서드로 다른 곳에서 사용할 수 있도록 @Bean으로 등록
 * BCryptPasswordEncoder() : password 암호화 방법
 * configure(HttpSecurity http) : HttpSecurity는 Http로 들어오는 요청에 대하여 보안을 구성할 수 있는 클래스
 * -- 로그인에 대한 설정
 *
 * AuthenticationManagerBuilder
 *  -- AuthenticationManager : Spring Security의 모든 인증 관리
 *  -- UserDetailService를 통해 유저의 정보를 memberService에서 찾아 담아줌
 */
@Configuration
@EnableWebSecurity

public class SecurityConfig{

    /**
     * TODO : Read https://stir.tistory.com/266
     * CSRF 공격과 방어 : https://junhyunny.github.io/information/security/spring-boot/spring-security/cross-site-reqeust-forgery/
     * http.csrf.disable() : CSRF 공격에 대한 방어 해제코드
     * antMatchers() : 특정 URL 접근시 인가가 필요한 URI 설정 가능
     *
     *
     */
    private final AuthenticationConfiguration authenticationConfiguration;
    private UserRepository userRepository;

    private  CorsConfig corsConfig;

    // 생성자 기반 DI 주입


    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, UserRepository userRepository, CorsConfig corsConfig) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.userRepository = userRepository;
        this.corsConfig = corsConfig;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     *
     * http.csrf().disable()
     * - REST API에서는 CSRF 방어가 필요없다 > CSRF 설정을 해제해도 된다.
     *
     * addFilter(corsConfig.corsFilter())
     * - REST API는 여러 서버를 운영하는 환경이라서 CORS를 허용해줘야한다.
     *
     *
     * sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     * - 서버를 Stateless하게 유지. Spring Security에서 세션을 만들지 않는다.
     *
     *
     * httpBasic().disable()
     * - request headerd에 id와 password를 직접 날리는 방식이라 보안에 광장히 취약함
     *
     * AuthorizationFilter : 사용자가 페이지 이동시 인가처리를 받기위한 필터
     * -
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .addFilter(corsConfig.corsFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()


                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and().build();
    }




}
