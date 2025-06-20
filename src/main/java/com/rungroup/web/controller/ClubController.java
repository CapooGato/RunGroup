package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping(value = "/clubs")
    public String listClubs(Model model){
       List<ClubDto> clubs = clubService.findAllClubs();
       model.addAttribute("clubs", clubs);
       return "clubs-list";
    }

    @GetMapping(value = "/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @GetMapping(value = "/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-detail";
    }

    @GetMapping(value = "/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-edit";
    }

    /**
     * This endpoint should be reseverd for admins only.
     * HTML doesn't support DELETE, we can use GET or POST, but POST will only work with <form ...></form>,
     * and GET will work with other tags, like <p>, <a> etc..
     * */
    @GetMapping(value = "/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }

    @GetMapping(value = "/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubsByTitle(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @PostMapping(value = "/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @PostMapping(value = "/clubs/{clubId}/edit") // can't use <form method="put"..> btw, only GET or POST
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result,
                             Model model) {
        if(result.hasErrors()){
            model.addAttribute("club", club);
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }

}
