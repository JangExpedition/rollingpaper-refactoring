package com.sh.rollingpaper.board.model.service;

import java.util.List;

import com.sh.rollingpaper.board.model.dto.Board;

public interface BoardService {

	List<Board> selectBoardList(int no);

	int insertBoard(Board board);

	Board selectOneBoard(int no);

	int deleteBoard(int no);

	int updateBoard(Board board);

}
