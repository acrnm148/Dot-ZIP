package com.mycom.myapp.qna.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaParamDto;
import com.mycom.myapp.qna.dto.QnaResultDto;

public interface QnaService {
	
	public QnaResultDto qnaDetail(int qnaId);
	public QnaResultDto qnaInsert(QnaDto dto, MultipartHttpServletRequest request);
	public QnaResultDto qnaList(QnaParamDto qnaParamDto);
	public QnaResultDto qnaAnswerInsert(QnaDto dto);
	public QnaResultDto qnaAnswerDetail(int qnaNum);
}
