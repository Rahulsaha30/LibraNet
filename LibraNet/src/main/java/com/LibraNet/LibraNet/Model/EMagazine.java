package com.LibraNet.LibraNet.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("EMagazine")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class EMagazine extends LibItem {
    private Integer issueNumber;

    public void archiveIssue() {
        System.out.println("Archiving issue " + issueNumber + " of " + getTitle());
    }
}
