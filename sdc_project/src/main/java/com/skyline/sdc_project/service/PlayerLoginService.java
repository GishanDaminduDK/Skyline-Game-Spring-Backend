package com.skyline.sdc_project.service;
import com.skyline.sdc_project.dto.PlayerDTO;
import com.skyline.sdc_project.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PlayerLoginService extends UserDetailsService {
    public PlayerDTO search(String username, String password) throws UserNotFoundException;
}
