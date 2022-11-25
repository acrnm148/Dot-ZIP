package com.mycom.myapp.user.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuthDao {
    int isRegisteredUser(String email);
}