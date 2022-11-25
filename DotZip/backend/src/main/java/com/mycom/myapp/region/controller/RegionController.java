package com.mycom.myapp.region.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.region.dto.RegionResultDto;
import com.mycom.myapp.region.service.RegionService;

@RestController
@CrossOrigin(
		// localhost:5500 �� 127.0.0.1 ����
		origins = "http://localhost:5500", // allowCredentials = "true" �� ���, orogins="*" �� X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
@RequestMapping("/regions")
public class RegionController {

	@Autowired
	RegionService service;
	
	private final String SUCCESS = "success";
	
	@GetMapping(value="/sido")
	public ResponseEntity<RegionResultDto> sidoList(){
		RegionResultDto regionResultDto = service.findSidoCodeList();
		if(regionResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<RegionResultDto>(regionResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<RegionResultDto>(regionResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/gugun/{sidoName}")
	public ResponseEntity<RegionResultDto> gugunList(@PathVariable("sidoName") String sidoName){
		System.out.println("controller:"+sidoName);
		RegionResultDto regionResultDto = service.findGugunCodeList(sidoName);
		if(regionResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<RegionResultDto>(regionResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<RegionResultDto>(regionResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/dong/{gugunName}")
	public ResponseEntity<RegionResultDto> dongList(@PathVariable("gugunName") String gugunName){
		RegionResultDto regionResultDto = service.findDongCodeList(gugunName);
		if(regionResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<RegionResultDto>(regionResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<RegionResultDto>(regionResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
