package com.sh.rollingpaper.security.model;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String msg = null;
		if(exception instanceof BadCredentialsException)
			msg = "아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인해 주세요.";
		else if(exception instanceof UsernameNotFoundException)
			msg = "존재하지 않는 아이디입니다.";
		
		msg = URLEncoder.encode(msg, "UTF-8");

		response.sendRedirect("/rollingpaper/?msg=" + msg);
	
	}
	
}
