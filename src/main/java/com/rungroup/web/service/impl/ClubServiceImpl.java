package com.rungroup.web.service.impl;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubList = clubRepository.findAll();
        return clubList.stream().map((club) -> mapToDto(club)).collect(Collectors.toList());
    }

    @Override
    public ClubDto saveClub(ClubDto clubDto) {
       Club newClub = mapToClub(clubDto);
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
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    public ClubDto mapToDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();

        return clubDto;
    }

    public Club mapToClub(ClubDto clubDto){
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .content(clubDto.getContent())
                .photoUrl(clubDto.getPhotoUrl())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();

        return club;
    }
}
