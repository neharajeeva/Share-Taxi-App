package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.models.CustomUser;

public interface CustomUserService {
    CustomUser registerUser(String username,String email, String password);
    CustomUser authenticateUser(String email, String password);
}
