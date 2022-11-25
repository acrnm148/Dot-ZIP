package com.mycom.myapp.interest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.interest.dto.InterestAreaDto;
import com.mycom.myapp.interest.dto.InterestParamDto;
import com.mycom.myapp.interest.dto.InterestResultDto;
import com.mycom.myapp.interest.service.InterestService;

@RestController 
@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class InterestController {

	@Autowired
	InterestService service;
	
	private static final int SUCCESS = 1;
	
	/**
	 * area 
	 */
	@GetMapping("/interest/area")
	public ResponseEntity<InterestResultDto> interestAreaList( int userSeq, int limit, int offset){

		InterestParamDto interestParamDto = new InterestParamDto();
//		System.out.println("session: "+session.getAttribute("userDto"));
//		int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq();
		interestParamDto.setUserSeq(userSeq);
		interestParamDto.setLimit(limit);
		interestParamDto.setOffset(offset);
		
		InterestResultDto interestResultDto = service.interestAreaList(interestParamDto);
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	@GetMapping(value="/interest/area/count/{userSeq}")
	public int getInterestAreaCountOfCurrentUser(@PathVariable("userSeq") int userSeq){
		int count = service.getInterestAreaCountOfCurrentUser(userSeq);
		return count; 
	}
	@GetMapping(value="/interest/house/count/{userSeq}")
	public int getInterestHouseCountOfCurrentUser(@PathVariable("userSeq") int userSeq){
		int count = service.getInterestHouseCountOfCurrentUser(userSeq);
		return count; 
	}
	@PostMapping("/interest/area/bookmark")
	public ResponseEntity<Boolean> getBookmarkArea(@RequestParam("dongCode") String dongCode,@RequestParam("userSeq") int userSeq) {
		System.out.println("SELECT bookmark!!!!");
		InterestAreaDto interestAreaDto = new InterestAreaDto();
		interestAreaDto.setUserSeq(userSeq);
		interestAreaDto.setDongCode(dongCode);
		System.out.println("userSeq : " + interestAreaDto.getUserSeq());
		System.out.println("dongCode : " + interestAreaDto.getDongCode());

		boolean ret = service.getBookmarkArea(interestAreaDto);
		return new ResponseEntity<Boolean>(ret, HttpStatus.OK);
	}
	
	
	@PostMapping("/interest/area/add")
	public ResponseEntity<InterestResultDto> interestAreaInsert(@RequestBody InterestParamDto interestParamDto, HttpSession session){
		
		InterestResultDto interestResultDto = service.interestAreaInsert(interestParamDto);
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/interest/area/delete")
	public ResponseEntity<InterestResultDto> interestAreaDelete(@RequestBody InterestParamDto interestParamDto, HttpSession session){

		
		InterestResultDto interestResultDto = service.interestAreaDelete(interestParamDto);
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * house 
	 */
	@GetMapping("/interest/house/{userSeq}")
	public ResponseEntity<InterestResultDto> interestHouseList(@PathVariable int userSeq){

		InterestParamDto interestParamDto = new InterestParamDto();
//		System.out.println("session: "+session.getAttribute("userDto"));
//		int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq();
		interestParamDto.setUserSeq(userSeq);
		
		InterestResultDto interestResultDto = service.interestHouseList(interestParamDto);
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	@PostMapping("/interest/house/add")
	public ResponseEntity<InterestResultDto> interestHouseInsert(@RequestParam("houseNo") int houseNo,@RequestParam("userSeq") int userSeq){
		InterestParamDto interestParamDto = new InterestParamDto();
		interestParamDto.setUserSeq(userSeq);
		interestParamDto.setHouseNo(houseNo);
		System.out.println(userSeq);
		InterestResultDto interestResultDto = service.interestHouseInsert(interestParamDto);
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/interest/house/delete")
	public ResponseEntity<InterestResultDto> interestHouseDelete(@RequestParam("houseNo") int houseNo,@RequestParam("userSeq") int userSeq){

		InterestParamDto interestParamDto = new InterestParamDto();
		interestParamDto.setUserSeq(userSeq);
		interestParamDto.setHouseNo(houseNo);
		
		InterestResultDto interestResultDto = service.interestHouseDelete(interestParamDto);
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * popular
	 */
	@GetMapping("/popular/house")
	public ResponseEntity<InterestResultDto> popularHouseList(){
		
		InterestResultDto interestResultDto = service.popularHouseList();
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/popular/area")
	public ResponseEntity<InterestResultDto> popularAreaList(){
		
		InterestResultDto interestResultDto = service.popularAreaList();
		
		if( interestResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<InterestResultDto>(interestResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
