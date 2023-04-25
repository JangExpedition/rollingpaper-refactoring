package com.sh.rollingpaper.security.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sh.rollingpaper.member.model.dto.Member;

@Mapper
public interface SecurityDao {

	@Select("SELECT * FROM MEMBER m left outer join AUTHORITY a on m.`NO` = a.`NO` WHERE NAME = #{username}")
	Member loadUserByUsername(String username);

}
