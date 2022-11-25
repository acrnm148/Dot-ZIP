package com.mycom.myapp.event.dto;

import java.util.Date;

public class EventResultDto {
	private int result;
	private EventDto dto;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public EventDto getDto() {
		return dto;
	}
	public void setDto(EventDto dto) {
		this.dto = dto;
	}
}
