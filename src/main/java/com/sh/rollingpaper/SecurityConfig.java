package com.sh.rollingpaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sh.rollingpaper.security.model.CustomizeAuthenticationFailureHandler;
import com.sh.rollingpaper.security.model.service.SecurityService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityService securityService;
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomizeAuthenticationFailureHandler();
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(securityService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception	{
    	web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**", "/index.jsp");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	// 인증이 필요한 경우
    	http.authorizeHttpRequests()
    		.antMatchers("/member/**").authenticated()
    		.antMatchers("/board/**").authenticated();
    	
    	// 로그인 설정
    	http.formLogin()
    		.loginProcessingUrl("/login")
    		.loginPage("/")
    		.defaultSuccessUrl("/member/memberList")
    		.failureHandler(authenticationFailureHandler());
    	
    	// 로그아웃 설정
    	http.logout()
    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    		.logoutSuccessUrl("/")
    		.invalidateHttpSession(true);
    	
    	// 오류 감지 시
    	http.exceptionHandling()
    		.accessDeniedPage("/WEB-INF/views/exception/exception.jsp");
    	
    }
    
    
}
