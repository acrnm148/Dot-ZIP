package com.mycom.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardFileDto;
import com.mycom.myapp.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	
	 BoardDto boardDetail(BoardParamDto boardParamDto);
	 List<BoardFileDto> boardDetailFileList(int boardId);
	
	 // get readCount
	 int boardUserReadCount(BoardParamDto boardParamDto); 
	 // insert readCount
	 int boardUserReadInsert( @Param("boardId") int boardId,@Param("userSeq") int userSeq ); 
	 // update readCount
	 int boardReadCountUpdate(int boardId);
	 
	 int boardDelete(int boardId);	
	 int boardFileDelete(int boardId);
	 List<String> boardFileUrlDeleteList(int boardId);
	 int boardReadCountDelete(int boardId);
	
	 int boardInsert(BoardDto dto);
	 int boardFileInsert(BoardFileDto dto);
	
	 List<BoardDto> boardList(BoardParamDto boardParamDto);
	 int boardListTotalCount();
	
	 List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
	 int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	

	 int boardUpdate(BoardDto dto);

}
