package com.mycom.myapp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// for boardList, boardDetail
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardParamDto {
	
	private int limit;
	private int offset;
	private String searchWord;
	
	private int boardId;
	private int userSeq;
}
