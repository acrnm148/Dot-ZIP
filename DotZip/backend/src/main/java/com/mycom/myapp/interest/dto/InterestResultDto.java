package com.mycom.myapp.interest.dto;

import java.util.List;

import com.mycom.myapp.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterestResultDto {
	private int result;
	private InterestAreaDto interestAreaDto;
	private InterestHouseDto interestHouseDto;
	private List<InterestAreaDto> arealist;
	private List<InterestHouseDto> houselist;
	private int count;
	
	private List<PopularAreaDto> popularArealist;
	private List<PopularHouseDto> popularHouselist;
}
