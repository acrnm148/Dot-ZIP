package com.mycom.myapp.event.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycom.myapp.event.dao.EventDao;
import com.mycom.myapp.event.dto.EventResultDto;

public class EventServiceImpl implements EventService{

	@Autowired
	EventDao eventDao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public EventResultDto getEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventResultDto eventDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
