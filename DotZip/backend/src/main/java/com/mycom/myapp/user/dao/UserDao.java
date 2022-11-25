package com.mycom.myapp.user.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.user.dto.UserDto;

@Mapper
public interface UserDao {
	UserDto userLogin(String userEmail);
	int userRegister(UserDto userDto);
	int userDupCheck(UserDto userDto);
	List<UserDto> getUsers(int userSeq);
	public UserDto userInfo(String userEmail) throws SQLException;
	int userChange(UserDto userDto);
	int userUnregist(UserDto userDto);
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userEmail) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
