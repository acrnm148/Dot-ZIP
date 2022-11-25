package com.mycom.myapp.region.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.region.dto.DongCodeDto;
import com.mycom.myapp.region.dto.GugunCodeDto;
import com.mycom.myapp.region.dto.SidoCodeDto;

@Mapper
public interface RegionDao {
	List<SidoCodeDto> findSidoCodeList(); 
	List<GugunCodeDto> findGugunCodeList(String sidoName);
	List<DongCodeDto> findDongCodeList(String gugunName);
}
