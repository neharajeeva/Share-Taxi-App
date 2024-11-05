package com.acs560.ShareTaxi.services;

import com.acs560.ShareTaxi.models.CustomUser;

public interface CustomUserService {
    CustomUser registerUser(String username, String password);
    CustomUser authenticateUser(String username, String password);
}
