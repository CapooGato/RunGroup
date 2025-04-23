package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.service.ClubService;
import com.rungroup.web.service.impl.ClubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {

    private final ClubServiceImpl clubService;

    @Autowired
    public ClubController(ClubServiceImpl clubService) {
        this.clubService = clubService;
    }

    @GetMapping(value = "/clubs")
    public String listClubs(Model model){
       List<ClubDto> clubs = clubService.findAllClubs();
       model.addAttribute("clubs", clubs);
       return "clubs-list";
    }
}
