package com.LibraNet.LibraNet.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("Audio")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Audio extends LibItem implements Play {

    private Integer durationInSeconds;

    @Override
    public void Play() {
        System.out.println("Playing audio: " + getTitle());
    }
}