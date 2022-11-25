package com.mycom.myapp.admin.dto;

import java.sql.Date;

public class EventUserDto {
	private int user_seq;
	private String user_name;
	private String user_password;
	private String user_email;
	private String user_profile_image_url;
	private Date user_register_date;
	private String user_clsf;
	private int event_seq;
	private String name;
	private int eventUserSeq;
	
	
	public EventUserDto(int user_seq, String user_name, String user_password, String user_email,
			String user_profile_image_url, Date user_register_date, String user_clsf, int event_seq, String name,
			int eventUserSeq) {
		super();
		this.user_seq = user_seq;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_email = user_email;
		this.user_profile_image_url = user_profile_image_url;
		this.user_register_date = user_register_date;
		this.user_clsf = user_clsf;
		this.event_seq = event_seq;
		this.name = name;
		this.eventUserSeq = eventUserSeq;
	}
	public EventUserDto() {
	}
	public String getUser_clsf() {
		return user_clsf;
	}
	public void setUser_clsf(String user_clsf) {
		this.user_clsf = user_clsf;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_profile_image_url() {
		return user_profile_image_url;
	}
	public void setUser_profile_image_url(String user_profile_image_url) {
		if( user_profile_image_url == null || "null".equals(user_profile_image_url) || "".equals(user_profile_image_url)) {
            this.user_profile_image_url = "/img/user/noProfile.png";
        }else {
            this.user_profile_image_url = user_profile_image_url;
        }
	}
	public Date getUser_register_date() {
		return user_register_date;
	}
	public void setUser_register_date(Date user_register_date) {
		this.user_register_date = user_register_date;
	}
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
	public int getEventUserSeq() {
		return eventUserSeq;
	}
	public void setEventUserSeq(int eventUserSeq) {
		this.eventUserSeq = eventUserSeq;
	}
	
	
}
