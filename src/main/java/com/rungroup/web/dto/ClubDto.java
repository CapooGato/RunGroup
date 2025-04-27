package com.rungroup.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDto {
    private Long id;

    @NotEmpty(message = "Title should not be empty")
    private String title;

    @NotEmpty(message = "Photo link should not be empty")
    private String photoUrl;

    @NotEmpty(message = "Content should not be empty")
    private String content;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
