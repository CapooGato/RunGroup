package com.rungroup.web.service;

import com.rungroup.web.dto.ClubDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    List<ClubDto> findAllClubs();
}
