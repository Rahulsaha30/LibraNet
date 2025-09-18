package com.LibraNet.LibraNet.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "borrow")
@Data
@NoArgsConstructor
@SuperBuilder
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private LibItem item;

    private String borrowerName;

    private LocalDate borrowedDate;
    private Integer durationTime;
    private LocalDate returnedDate;
    private Double fine = 0.0;
}