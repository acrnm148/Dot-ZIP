package com.mycom.myapp.event.dto;

import java.util.Date;

public class EventDto {
	int event_seq;
	String name;
	Date fromTime;
	Date endTime;
	int author_seq;
	Date createdAt;
	String use_yn;
	String url;
	String img;
	
	public int getEvent_seq() {
		return event_seq;
	}
	public void setEvent_seq(int event_seq) {
		this.event_seq = event_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public int getAuthor_seq() {
		return author_seq;
	}
	public void setAuthor_seq(int author_seq) {
		this.author_seq = author_seq;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		if( url == null || "null".equals(url) || "".equals(url)) {
            this.url = "/img/no_img.png"; 
        }else {
            this.url = url;
        }
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
