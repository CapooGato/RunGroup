package com.rungroup.web.service;

import com.rungroup.web.dto.EventDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
