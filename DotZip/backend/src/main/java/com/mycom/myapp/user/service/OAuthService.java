package com.mycom.myapp.user.service;

import java.util.HashMap;

public interface OAuthService {

    HashMap<String, String> isRegisteredUser(String email);
}