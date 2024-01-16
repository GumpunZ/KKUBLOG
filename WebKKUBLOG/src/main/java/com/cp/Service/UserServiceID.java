package com.cp.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserServiceID extends UserDetails {
    int getId();
    void setId(int id);
}
