package com.sh.rollingpaper.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sh.rollingpaper.member.model.dto.Member;

@Mapper
public interface MemberDao {

	Member selectOneMember(String name);

	List<Member> selectAllMember(Member loginMember);

	int insertMember(Member member);

	int updateMember(Member member);

	Member selectOneMemberByNo(int no);

}
