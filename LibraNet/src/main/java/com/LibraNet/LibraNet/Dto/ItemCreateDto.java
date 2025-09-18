package com.LibraNet.LibraNet.Dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCreateDto {
    @NotBlank
    private String title;
    @NotBlank
    private String authorName;
    @NotBlank
    private String itemType;
    private Integer pageCount;
    private Integer durationInSeconds;
    private Integer issueNumber;
}