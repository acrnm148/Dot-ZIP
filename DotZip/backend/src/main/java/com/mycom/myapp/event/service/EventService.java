package com.mycom.myapp.event.service;

import java.util.List;

import com.mycom.myapp.event.dto.EventDto;
import com.mycom.myapp.event.dto.EventResultDto;

public interface EventService {
	//int eventRegister(EventDto eventDto);
	EventResultDto getEvent();
	EventResultDto eventDetail();
	
	//public int eventDelete(int eventSeq);
}
