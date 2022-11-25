package com.mycom.myapp.region.service;


import com.mycom.myapp.region.dto.RegionResultDto;

public interface RegionService {
	RegionResultDto findSidoCodeList();
	RegionResultDto findGugunCodeList(String sidoName);
	RegionResultDto findDongCodeList(String gugunName);
}
