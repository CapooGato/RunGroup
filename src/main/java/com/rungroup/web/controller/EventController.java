package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;
import com.rungroup.web.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/events")
    public String eventList(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping(value = "/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping(value = "/events/{eventId}/edit")
    public String editEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @GetMapping(value = "/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping(value = "/events/{clubId}")
    public String creatEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "clubs-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @PostMapping(value = "/events/{eventId}/edit") // can't use <form method="put"..> btw, only GET or POST
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("club") EventDto eventDto, /* we are getting data form form */
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "events-edit";
        }
        EventDto eventDto1 = eventService.findByEventId(eventId);
        eventDto.setClub(eventDto1.getClub()); /* Because we used Lazy loading, the club won't be automatically loaded
                                                  with event, therefore we need to set it by hand */

        eventDto.setId(eventId);
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping(value = "/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEventById(eventId);
        return "redirect:/events";
    }
}
