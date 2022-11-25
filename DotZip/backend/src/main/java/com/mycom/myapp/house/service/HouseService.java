package com.mycom.myapp.house.service;

import java.util.List;

import com.mycom.myapp.house.dto.HouseDealDetailDto;
import com.mycom.myapp.house.dto.HouseDealParamDto;
import com.mycom.myapp.house.dto.HouseDealSimpleDto;

public interface HouseService {
	List<HouseDealSimpleDto> findHouseDealsByDongCode(HouseDealParamDto houseDealParamDto);
	int houseDealsByDongCodeTotalCnt(String dongName);
	List<HouseDealSimpleDto> findHouseDealsByAptName(HouseDealParamDto houseDealParamDto);
	int houseDealsByAptNameTotalCnt(String searchWord);

	HouseDealDetailDto findHouseDealDetail(int no);
}
