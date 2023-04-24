package com.sh.rollingpaper.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.rollingpaper.board.model.dao.BoardDao;
import com.sh.rollingpaper.board.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> selectBoardList(int no) {
		return boardDao.selectBoardList(no);
	}

	@Override
	public int insertBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	@Override
	public Board selectOneBoard(int no) {
		return boardDao.selectOneBoard(no);
	}

	@Override
	public int deleteBoard(int no) {
		return boardDao.deleteBoard(no);
	}

	@Override
	public int updateBoard(Board board) {
		return boardDao.updateBoard(board);
	}
}
