package com.mycom.myapp.qna.controller;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import com.mycom.myapp.board.service.BoardService;
import com.mycom.myapp.interest.dto.InterestParamDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaParamDto;
import com.mycom.myapp.qna.dto.QnaResultDto;
import com.mycom.myapp.qna.service.QnaService;
import com.mycom.myapp.user.dto.UserDto;

@RestController
@CrossOrigin(
		origins = "http://localhost:5500", 
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class QnaController {

	@Autowired
	QnaService service;
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/qna")
	public ResponseEntity<QnaResultDto> boardList(QnaParamDto qnaParamDto){
		
		QnaResultDto qnaResultDto = service.qnaList(qnaParamDto);
		
		if( qnaResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/qna/{qnaId}")
	public ResponseEntity<QnaResultDto> qnaDetail(@PathVariable int qnaId){//@RequestParam("userSeq")
		
		QnaResultDto qnaResultDto = service.qnaDetail(qnaId);
				
		if( qnaResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@GetMapping(value="/qna/ans/{qnaNum}")
	public ResponseEntity<QnaResultDto> qnaAnswerDetail(@PathVariable int qnaNum){//@RequestParam("userSeq")
		System.out.println("qnaAnswerDetail ³Ñ¾î¿È");
		QnaResultDto qnaResultDto = service.qnaAnswerDetail(qnaNum);

		if( qnaResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/qna")
	public ResponseEntity<QnaResultDto> qnaInsert(
			QnaDto qnaDto,
			MultipartHttpServletRequest request) {
		System.out.println("insert"+qnaDto.getUserSeq());
		
		QnaResultDto qnaResultDto = service.qnaInsert(qnaDto, request); //boardInsert(qnaDto, request);
		
		if( qnaResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/qna/ans")
	public ResponseEntity<QnaResultDto> qnaAnswerInsert(
			QnaDto qnaDto) {
		System.out.println("insert"+qnaDto.getUserSeq());
		
		QnaResultDto qnaResultDto = service.qnaAnswerInsert(qnaDto);
		
		if( qnaResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
}
