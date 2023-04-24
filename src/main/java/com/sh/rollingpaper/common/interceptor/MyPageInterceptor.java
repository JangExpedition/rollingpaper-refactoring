package com.sh.rollingpaper.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.sh.rollingpaper.member.model.dto.Member;

public class MyPageInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		int no = Integer.parseInt(request.getParameter("no"));
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if(loginMember.getNo() != no) {
			// FlashMap사용 - RedirectAttributes 사용불가
			FlashMap flashMap = new FlashMap();
			flashMap.put("msg", "본인 페이지가 아닙니다. 돌아가세요^^");
			FlashMapManager manager = RequestContextUtils.getFlashMapManager(request);
			manager.saveOutputFlashMap(flashMap, request, response);
			
			response.sendRedirect(request.getContextPath() + "/member/memberList.do");
			
			return false; // handler 호출하지 않음.
		}
		
		return super.preHandle(request, response, handler);
	}
}
