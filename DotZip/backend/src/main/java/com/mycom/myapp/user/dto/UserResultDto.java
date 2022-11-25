package com.mycom.myapp.user.dto;

public class UserResultDto {
	private int result;
	private UserDto dto;

	public UserDto getDto() {
		return dto;
	}

	public void setDto(UserDto dto) {
		this.dto = dto;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}