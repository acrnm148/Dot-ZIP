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
public class InterestParamDto {
	
	private int limit;
	private int offset;

	private int dongCode;
	private int houseNo;
	private int userSeq;
}
