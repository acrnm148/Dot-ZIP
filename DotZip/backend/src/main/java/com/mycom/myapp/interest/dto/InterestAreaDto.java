package com.mycom.myapp.interest.dto;

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
public class InterestAreaDto {

	private int interestAreaId;
	private int userSeq;
	private String dongCode;
	private String cityCode;
	private String gugunCode;
	private String cityName;//sido
	private String gugunName;//gugun
	private String name;//dong
	private int bookmarked;
	
}
