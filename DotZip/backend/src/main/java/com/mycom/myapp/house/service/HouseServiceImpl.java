package com.mycom.myapp.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.house.dao.HouseDao;
import com.mycom.myapp.house.dto.HouseDealDetailDto;
import com.mycom.myapp.house.dto.HouseDealParamDto;
import com.mycom.myapp.house.dto.HouseDealSimpleDto;

@Service
public class HouseServiceImpl implements HouseService{
	@Autowired
	HouseDao houseDao;
	
	@Override
	public List<HouseDealSimpleDto> findHouseDealsByDongCode(HouseDealParamDto houseDealParamDto) {
		List<HouseDealSimpleDto> list = null;
			
		try {
			list = houseDao.findHouseDealsByDongCode(houseDealParamDto.getDong(),houseDealParamDto.getNo(), houseDealParamDto.getLimit(), houseDealParamDto.getOffset());
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}
	@Override
	public int houseDealsByDongCodeTotalCnt(String dong) {
		int count = 0;
		try {
			count = houseDao.houseDealsByDongCodeTotalCnt(dong);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public List<HouseDealSimpleDto> findHouseDealsByAptName(HouseDealParamDto houseDealParamDto) {
		List<HouseDealSimpleDto> list = null;
		
		try {
			list = houseDao.findHouseDealsByAptName(houseDealParamDto.getSearchWord(),houseDealParamDto.getNo(), houseDealParamDto.getLimit(), houseDealParamDto.getOffset());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int houseDealsByAptNameTotalCnt(String searchWord) {
		int count = 0;
		try {
			count = houseDao.houseDealsByAptNameTotalCnt(searchWord);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public HouseDealDetailDto findHouseDealDetail(int no) {
		HouseDealDetailDto dto = null;
		try {
			dto = houseDao.findHouseDealDetail(no);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return dto;
	}
	
	
}
