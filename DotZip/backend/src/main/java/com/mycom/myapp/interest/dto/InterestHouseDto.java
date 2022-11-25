package com.mycom.myapp.interest.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.mycom.myapp.board.dto.BoardFileDto;

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
public class InterestHouseDto {

	private int interestHouseId;
	private int userSeq;
	private int houseNo;
	private String dong;
	private String aptName;
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String area;
	private String lat;
	private String lng;
	private int bookmarked;
}
