package com.rungroup.web.service.impl;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.repository.UserRepository;
import com.rungroup.web.security.SecurityUtil;
import com.rungroup.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.web.mapper.ClubMapper.mapToClub;
import static com.rungroup.web.mapper.ClubMapper.mapToDto;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubList = clubRepository.findAll();
        return clubList.stream().map((club) -> mapToDto(club)).collect(Collectors.toList());
    }

    @Override
    public ClubDto saveClub(ClubDto clubDto) {
        /* Pobieramy username aktualnego użytkownika */
       String username = SecurityUtil.getSessionUser();
       UserEntity user = userRepository.findByUsername(username);

       Club newClub = mapToClub(clubDto);
       /* Ustawiamy aktualnego użytkownika w sesji jako twórce klubu który teraz tworzymy */
       newClub.setCreatedBy(user);
       Club savedClub = clubRepository.save(newClub);
       return mapToDto(savedClub);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubsByTitle(String query) {
        List<Club> clubs = clubRepository.searchClub(query);
        return clubs.stream().map((c) -> mapToDto(c)).collect(Collectors.toList());
    }

}
