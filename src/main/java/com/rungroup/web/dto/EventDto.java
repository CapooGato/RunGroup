package com.rungroup.web.dto;

import com.rungroup.web.models.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String type;
    private String photoUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Club club;  /* It should be clubDto, but im following tutorial so meow*/
}
