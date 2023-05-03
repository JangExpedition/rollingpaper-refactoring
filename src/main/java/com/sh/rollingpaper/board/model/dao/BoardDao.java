package com.sh.rollingpaper.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sh.rollingpaper.board.model.dto.Board;

@Mapper
public interface BoardDao {

	@Select("select * from rollingpaper.`BOARD` where owner_no = #{ownerNo}")
	List<Board> selectBoardList(int ownerNo);

	int insertBoard(Board board);

	Board selectOneBoard(int no);

	int deleteBoard(int no);

	int updateBoard(Board board);

}
