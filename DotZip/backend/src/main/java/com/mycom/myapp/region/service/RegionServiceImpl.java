package com.mycom.myapp.region.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.region.dao.RegionDao;
import com.mycom.myapp.region.dto.DongCodeDto;
import com.mycom.myapp.region.dto.GugunCodeDto;
import com.mycom.myapp.region.dto.RegionResultDto;
import com.mycom.myapp.region.dto.SidoCodeDto;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	RegionDao regionDao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@Override
	public RegionResultDto findSidoCodeList() {
		RegionResultDto regionResultDto = new RegionResultDto();
		try {
			List<SidoCodeDto> list = regionDao.findSidoCodeList();
			regionResultDto.setSidoList(list);
			regionResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			regionResultDto.setResult(FAIL);
		}
		return regionResultDto;
	}
	
	@Override
	public RegionResultDto findGugunCodeList(String sidoName) {
		RegionResultDto regionResultDto = new RegionResultDto();
		System.out.println("impl:"+sidoName);
		try {
			List<GugunCodeDto> list = regionDao.findGugunCodeList(sidoName);
			System.out.println("service:"+list);
			regionResultDto.setGugunList(list);
			regionResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			regionResultDto.setResult(FAIL);
		}
		return regionResultDto;
	}

	@Override
	public RegionResultDto findDongCodeList(String gugunName) {
		RegionResultDto regionResultDto = new RegionResultDto();
		
		try {
			List<DongCodeDto> list = regionDao.findDongCodeList(gugunName);
			regionResultDto.setDongList(list);
			regionResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			regionResultDto.setResult(FAIL);
		}
		return regionResultDto;
	}

	

}
