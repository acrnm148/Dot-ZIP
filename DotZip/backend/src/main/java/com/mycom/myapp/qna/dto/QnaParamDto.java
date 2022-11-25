package com.mycom.myapp.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaParamDto {
	
	private int limit;
	private int offset;
	
	private int qnaId;
	private int qnaNum;
	private int userSeq;
}
