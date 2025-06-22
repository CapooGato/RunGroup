package com.rungroup.web.service;

import com.rungroup.web.dto.RegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(RegistrationDto registrationDto);
}
