package com.LibraNet.LibraNet.Dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Integer id;
    private String title;
    private String authorName;
    private String itemType;
    private boolean available;
    private Integer pageCount;
    private Integer durationInSeconds;
    private Integer issueNumber;
}
