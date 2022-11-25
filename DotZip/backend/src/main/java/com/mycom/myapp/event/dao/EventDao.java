package com.mycom.myapp.event.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.event.dto.EventDto;

@Mapper
public interface EventDao {
	int eventList();
	int eventListOne(EventDto eventDto);
}
