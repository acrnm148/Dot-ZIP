package com.mycom.myapp.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.house.dto.HouseDealDetailDto;
import com.mycom.myapp.house.dto.HouseDealSimpleDto;

@Mapper
public interface HouseDao {
	List<HouseDealSimpleDto> findHouseDealsByDongCode(@Param("dong") String dong,@Param("no") int no,@Param("limit") int limit,@Param("offset") int offset);
	int houseDealsByDongCodeTotalCnt(String dong);
	List<HouseDealSimpleDto> findHouseDealsByAptName(@Param("searchWord") String searchWord,@Param("no") int no,@Param("limit") int limit,@Param("offset") int offset);
	int houseDealsByAptNameTotalCnt(String searchWord);
	
	HouseDealDetailDto findHouseDealDetail(int no);
}
