package com.mycom.myapp.qna.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dao.BoardDao;
import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardFileDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import com.mycom.myapp.qna.dao.QnaDao;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaFileDto;
import com.mycom.myapp.qna.dto.QnaParamDto;
import com.mycom.myapp.qna.dto.QnaResultDto;

	@Service
	public class QnaServiceImpl implements QnaService {
	
		@Autowired
		QnaDao dao;
		
		@Value("${app.fileupload.uploadDir}")
		String uploadFolder;
		
		@Value("${app.fileupload.uploadPath}")
		String uploadPath;
		
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	@Transactional
	public QnaResultDto qnaDetail(int qnaId) {
		
		QnaResultDto qnaResultDto = new QnaResultDto();
		
		try {
			List<QnaDto> dtoList = dao.qnaDetail(qnaId);
			QnaDto dto = dao.qnaQuestion(qnaId);
			System.out.println("-----"+dto);
			List<QnaFileDto> fileList = dao.qnaDetailFileList(dto.getQnaId());
			int cnt = dao.qnaDetailCount(qnaId);

			dto.setFileList(fileList);
			qnaResultDto.setDto(dto);
			qnaResultDto.setList(dtoList);
			qnaResultDto.setCount(cnt);
			
			qnaResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			qnaResultDto.setResult(FAIL);
		}
		
		return qnaResultDto;
	}

	@Override
	public QnaResultDto qnaList(QnaParamDto qnaParamDto) {
		
		QnaResultDto qnaResultDto = new QnaResultDto();
		
		try {
			List<QnaDto> list = dao.qnaList(qnaParamDto);
			int count = dao.qnaListTotalCount();			
			qnaResultDto.setList(list);
			qnaResultDto.setCount(count);
			qnaResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			qnaResultDto.setResult(FAIL);
		}
		
		return qnaResultDto;
	}

	@Override
	public QnaResultDto qnaInsert(QnaDto dto, MultipartHttpServletRequest request) {
		QnaResultDto qnaResultDto = new QnaResultDto();
		
		try {
			dto.setQnaNum(dto.getQnaId());
			dao.qnaInsert(dto); // useGeneratedKeys="true" keyProperty="boardId"
			
			List<MultipartFile> fileList = request.getFiles("file");
	
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdir();

			for (MultipartFile part : fileList) {

				int qnaId = dto.getQnaId();
				
				String fileName = part.getOriginalFilename();
				
				//Random File Id
				UUID uuid = UUID.randomUUID();
				
				//file extension
				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
			
				String savingFileName = uuid + "." + extension;
			
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				part.transferTo(destFile);
		    
			    // Table Insert
			    QnaFileDto qnaFileDto = new QnaFileDto();
			    qnaFileDto.setQnaId(qnaId);
			    qnaFileDto.setFileName(fileName);
			    qnaFileDto.setFileSize(part.getSize());
			    qnaFileDto.setFileContentType(part.getContentType());
				String qnaFileUrl = uploadFolder + "/" + savingFileName;
				qnaFileDto.setFileUrl(qnaFileUrl);
				
				dao.qnaFileInsert(qnaFileDto);
			}

			qnaResultDto.setResult(SUCCESS);
			
		}catch(IOException e) {
			e.printStackTrace();
			qnaResultDto.setResult(FAIL);
		}
		return qnaResultDto;
	}
	
	@Override
	public QnaResultDto qnaAnswerInsert(QnaDto dto) {
		QnaResultDto qnaResultDto = new QnaResultDto();
		
		try {
			dao.qnaAnswerInsert(dto);
			dao.ansYNUpdate(dto.getQnaNum());
			
			qnaResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			qnaResultDto.setResult(FAIL);
		}
		return qnaResultDto;
	}

	@Override
	public QnaResultDto qnaAnswerDetail(int qnaNum) {
		QnaResultDto qnaResultDto = new QnaResultDto();
		
		try {
			QnaDto dto = dao.qnaAnswerDetail(qnaNum);

			qnaResultDto.setDto(dto);
			
			qnaResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			qnaResultDto.setResult(FAIL);
		}
		
		return qnaResultDto;
	}
}