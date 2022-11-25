package com.mycom.myapp.interest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.interest.dao.InterestDao;
import com.mycom.myapp.interest.dto.InterestAreaDto;
import com.mycom.myapp.interest.dto.InterestHouseDto;
import com.mycom.myapp.interest.dto.InterestParamDto;
import com.mycom.myapp.interest.dto.InterestResultDto;
import com.mycom.myapp.interest.dto.PopularAreaDto;
import com.mycom.myapp.interest.dto.PopularHouseDto;

@Service
public class InterestServiceImpl implements InterestService{

	@Autowired
	InterestDao dao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	/*
	 * interestarea
	 * */
	@Override
	public InterestResultDto interestAreaList(InterestParamDto interestParamDto) {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			List<InterestAreaDto> list = dao.interestAreaList(interestParamDto);
			interestResultDto.setArealist(list);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	@Override
	public InterestResultDto interestAreaInsert(InterestParamDto interestParamDto) {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			dao.interestAreaInsert(interestParamDto);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	@Override
	public InterestResultDto interestAreaDelete(InterestParamDto interestParamDto) {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			dao.interestAreaDelete(interestParamDto);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	/*
	 * interest house
	 * */
	@Override
	public InterestResultDto interestHouseList(InterestParamDto interestParamDto) {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			List<InterestHouseDto> list = dao.interestHouseList(interestParamDto);
			interestResultDto.setHouselist(list);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	@Override
	public InterestResultDto interestHouseInsert(InterestParamDto interestParamDto) {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			dao.interestHouseInsert(interestParamDto);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	@Override
	public InterestResultDto interestHouseDelete(InterestParamDto interestParamDto) {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			dao.interestHouseDelete(interestParamDto);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}


	@Override
	public InterestResultDto popularHouseList() {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			List<PopularHouseDto> list = dao.popularHouseList();
			interestResultDto.setPopularHouselist(list);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	@Override
	public InterestResultDto popularAreaList() {
		InterestResultDto interestResultDto = new InterestResultDto();
		
		try {
			List<PopularAreaDto> list = dao.popularAreaList();
			interestResultDto.setPopularArealist(list);
			interestResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			interestResultDto.setResult(FAIL);
		}
		return interestResultDto;
	}

	@Override
	public boolean getBookmarkArea(InterestAreaDto interestAreaDto) {
		return dao.interestArea(interestAreaDto);
	}

	@Override
	public int getInterestAreaCountOfCurrentUser(int userSeq) {
		return dao.getInterestAreaCountOfCurrentUser(userSeq);
	}

	@Override
	public int getInterestHouseCountOfCurrentUser(int userSeq) {
		return dao.getInterestHouseCountOfCurrentUser(userSeq);
	}

}
