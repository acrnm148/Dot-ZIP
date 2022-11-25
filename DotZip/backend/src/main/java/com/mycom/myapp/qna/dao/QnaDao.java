package com.mycom.myapp.qna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardFileDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaFileDto;
import com.mycom.myapp.qna.dto.QnaParamDto;

@Mapper
public interface QnaDao {
	
	 QnaDto qnaQuestion(int qnaId);
	 List<QnaDto> qnaDetail(int qnaId);
	 List<QnaFileDto> qnaDetailFileList(int qnaId);
	 int qnaDetailCount(int qnaId);
	 
	 int qnaDelete(int qnaId);	
	 int qnaFileDelete(int qnaId);
	 List<String> qnaFileUrlDeleteList(int qnaId);
	
	 int qnaInsert(QnaDto dto);
	 int qnaFileInsert(QnaFileDto dto);
	
	 List<QnaDto> qnaList(QnaParamDto qnaParamDto);
	 int qnaListTotalCount();
	 
	 int qnaAnswerInsert(QnaDto dto);
	 int ansYNUpdate(int qnaId);
	 
	 QnaDto qnaAnswerDetail(int qnaNum);
}
