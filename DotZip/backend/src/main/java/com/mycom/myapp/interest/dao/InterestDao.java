package com.mycom.myapp.interest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.interest.dto.InterestAreaDto;
import com.mycom.myapp.interest.dto.InterestHouseDto;
import com.mycom.myapp.interest.dto.InterestParamDto;
import com.mycom.myapp.interest.dto.InterestResultDto;
import com.mycom.myapp.interest.dto.PopularAreaDto;
import com.mycom.myapp.interest.dto.PopularHouseDto;

@Mapper
public interface InterestDao {

	//interest area
	List<InterestAreaDto> interestAreaList(InterestParamDto interestParamDto);
	int getInterestAreaCountOfCurrentUser(int userSeq);
	int interestAreaInsert(InterestParamDto interestParamDto);
	int interestAreaDelete(InterestParamDto interestParamDto);
	boolean interestArea(InterestAreaDto interestAreaDto);
	//interest house
	List<InterestHouseDto> interestHouseList(InterestParamDto interestParamDto);
	int getInterestHouseCountOfCurrentUser(int userSeq);
	int interestHouseInsert(InterestParamDto interestParamDto);
	int interestHouseDelete(InterestParamDto interestParamDto);

	//popular
	List<PopularHouseDto> popularHouseList();
	List<PopularAreaDto> popularAreaList();
}
