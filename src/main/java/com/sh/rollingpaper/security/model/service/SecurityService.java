package com.sh.rollingpaper.security.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sh.rollingpaper.member.model.dto.Member;
import com.sh.rollingpaper.security.model.dao.SecurityDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityService implements UserDetailsService{

	@Autowired
	private SecurityDao securityDao;
	
	public SecurityService(SecurityDao securityDao) {
		this.securityDao = securityDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = securityDao.loadUserByUsername(username);
		return member;
	}

}
