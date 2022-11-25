package com.mycom.myapp.admin.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardResultDto;

public interface AdminService {

	//공지사항
	public BoardResultDto boardDelete(int boardId);
	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);

}
