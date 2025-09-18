package com.LibraNet.LibraNet.Dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowedRecordDto {
    private Long id;
    private Integer itemId;
    private String borrowerName;
    private LocalDate borrowedOn;
    private Integer durationDays;
    private LocalDate returnedOn;
    private Double fine;
}

