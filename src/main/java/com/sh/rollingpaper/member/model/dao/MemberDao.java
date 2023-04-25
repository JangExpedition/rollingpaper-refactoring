package com.sh.rollingpaper.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

import com.sh.rollingpaper.member.model.dto.Member;

@Mapper
public interface MemberDao {

	Member selectOneMember(String name);

	@Select("select * from rollingpaper.`MEMBER` where name != #{name} order by name")
	List<Member> selectAllMember(Member loginMember);

	int insertMember(Member member);

	int updateMember(Member member);

	Member selectOneMemberByNo(int no);

}
