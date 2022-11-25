package com.mycom.myapp.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;

public interface BoardService {
	
	public BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	public BoardResultDto boardList(BoardParamDto boardParamDto);
	//public int boardListTotalCount();	
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	//public int boardListSearchWordTotalCount(boardParamDto boardParamDto);

}
