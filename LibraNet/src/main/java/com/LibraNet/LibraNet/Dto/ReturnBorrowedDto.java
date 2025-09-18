package com.LibraNet.LibraNet.Dto;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnBorrowedDto {
    @NotNull
    private Integer itemId;
    @NotBlank
    private String borrowerName;
}

