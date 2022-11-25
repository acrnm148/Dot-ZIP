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
public class PopularHouseDto {

	private int houseNo;
	private String aptName;
	private String dong;
	private int cnt;
	
}
