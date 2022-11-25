package com.mycom.myapp.qna.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
public class QnaDto {
	private int qnaId;
	private int userSeq;
	private String userEmail;
	private int type;
	private String title;
	private String content;
	private LocalDateTime regDt;
	private String ansYN;
	private String password;
	private int qnaNum;
	
	private List<QnaFileDto> fileList;

	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(
				regDt.toInstant(), ZoneId.systemDefault()
		);
	}

}

