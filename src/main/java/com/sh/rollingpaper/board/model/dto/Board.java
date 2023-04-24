package com.sh.rollingpaper.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private int no;
	private int ownerNo;
	private String writer;
	private String title;
	private String content;
}
