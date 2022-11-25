package com.mycom.myapp.user.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dao.OAuthDao;

@Service
public class OAuthServiceImpl implements OAuthService{

@Autowired
OAuthDao oauthDao;

@Override
public HashMap<String, String> isRegisteredUser(String email) {    
    HashMap<String, String> map = new HashMap<String, String>();
    if( oauthDao.isRegisteredUser(email) == 1 ) {
        map.put("result", "registered");
    }else {
        map.put("result", "not_registered");
    }
    return map;
}
}