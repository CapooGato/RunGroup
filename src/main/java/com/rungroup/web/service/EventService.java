package com.rungroup.web.service;

import com.rungroup.web.dto.EventDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void updateEvent(@Valid EventDto eventDto);

    void deleteEventById(Long eventId);
}
