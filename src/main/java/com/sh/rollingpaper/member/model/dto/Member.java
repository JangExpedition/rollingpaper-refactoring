package com.sh.rollingpaper.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private int no;
	@NonNull
	private String name;
	@NonNull
	private String password;
}
