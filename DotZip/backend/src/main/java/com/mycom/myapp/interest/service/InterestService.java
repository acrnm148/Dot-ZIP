package com.mycom.myapp.interest.service;

import com.mycom.myapp.interest.dto.InterestAreaDto;
import com.mycom.myapp.interest.dto.InterestParamDto;
import com.mycom.myapp.interest.dto.InterestResultDto;

public interface InterestService {

	//interest area
	public InterestResultDto interestAreaList(InterestParamDto interestParamDto);
	public InterestResultDto interestAreaInsert(InterestParamDto interestParamDto);
	public InterestResultDto interestAreaDelete(InterestParamDto interestParamDto);
	public boolean getBookmarkArea(InterestAreaDto interestAreaDto);
	public int getInterestAreaCountOfCurrentUser(int userSeq);
	//interest house
	public InterestResultDto interestHouseList(InterestParamDto interestParamDto);
	public InterestResultDto interestHouseInsert(InterestParamDto interestParamDto);
	public InterestResultDto interestHouseDelete(InterestParamDto interestParamDto);
	public int getInterestHouseCountOfCurrentUser(int userSeq);
	
	//popular
	public InterestResultDto popularHouseList();
	public InterestResultDto popularAreaList();
}
