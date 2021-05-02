package com.udacity.jdnd.course3.critter.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pet {
    @Id
    @GeneratedValue
    private Long petId;

    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;

    private LocalDateTime birthDate;
    @ElementCollection
    private List<String> notes;

}
