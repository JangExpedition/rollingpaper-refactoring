package com.sh.rollingpaper.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sh.rollingpaper.member.model.dao.MemberDao;
import com.sh.rollingpaper.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public Member selectOneMember(String name) {
		return memberDao.selectOneMember(name);
	}

	@Override
	public List<Member> selectAllMemberWithoutMe(String name) {
		return memberDao.selectAllMemberWithoutMe(name);
	}

	@Override
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public Member selectOneMemberByNo(int no) {
		return memberDao.selectOneMemberByNo(no);
	}

}
