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
public class PopularAreaDto {

	private String code;
	private String cityName;
	private String gugunName;
	private String name;
	private int cnt;
	
}
