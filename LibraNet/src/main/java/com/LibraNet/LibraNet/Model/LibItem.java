package com.LibraNet.LibraNet.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name="LibItem")
@Data
@DiscriminatorColumn(name="item_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@SuperBuilder
public class LibItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer id;
    private String authorName;
    private   String title;
    private  Boolean available=true;

}
