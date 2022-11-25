package com.mycom.myapp.house.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.house.dto.HouseDealDetailDto;
import com.mycom.myapp.house.dto.HouseDealParamDto;
import com.mycom.myapp.house.dto.HouseDealSimpleDto;
import com.mycom.myapp.house.service.HouseService;

@RestController // controller + responsebody
@CrossOrigin(
		origins = "http://localhost:5500", // allowCredentials = "true" �� ���, orogins="*" �� X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
@RequestMapping("/houses")
public class HouseController {
	
	@Autowired
	HouseService service;
	
	@PostMapping(value="/dong")
	public List<HouseDealSimpleDto> findHouseDealsByDongCode(@RequestBody HouseDealParamDto houseDealParamDto){
		System.out.println("검색 동이름 : " + houseDealParamDto.getDong());
		if (houseDealParamDto.getDong().isEmpty()) {
			return null;
		} else {
			return service.findHouseDealsByDongCode(houseDealParamDto);
		}
	}
	
	@GetMapping(value="/dong/count/{dong}")
	public int houseDealsByDongCodeTotalCnt(@PathVariable("dong") String dong){
		int count = service.houseDealsByDongCodeTotalCnt(dong);
		return count; 
	}
	
	@PostMapping(value="/apts")
	public List<HouseDealSimpleDto> findHouseDealsByAptName(@RequestBody HouseDealParamDto houseDealParamDto){
		System.out.println("검색 이름 : " + houseDealParamDto.getSearchWord());
		if (houseDealParamDto.getSearchWord().isEmpty()) {
			return null;
		} else {
			return service.findHouseDealsByAptName(houseDealParamDto);
		}
	}
	
	@GetMapping(value="/apts/count/{searchWord}")
	public int houseDealsByAptNameTotalCnt(@PathVariable("searchWord") String searchWord){
		int count = service.houseDealsByAptNameTotalCnt(searchWord);
		return count;
	}
	
	@GetMapping(value="/{no}")
	public HouseDealDetailDto findHouseDealDetail(@PathVariable int no){
		HouseDealDetailDto dto = service.findHouseDealDetail(no);
		return dto;
	}
}
