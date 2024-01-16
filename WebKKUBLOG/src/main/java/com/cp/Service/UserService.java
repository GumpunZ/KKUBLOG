package com.cp.Service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.cp.DTO.UserRegisterDTO;
import com.cp.Model.User;

public interface UserService extends UserDetailsService{
    
    User save(UserRegisterDTO userRegisterDTO);
}
