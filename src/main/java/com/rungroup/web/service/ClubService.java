package com.rungroup.web.service;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    List<ClubDto> findAllClubs();
    ClubDto saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);

    void deleteClub(Long clubId);
}
