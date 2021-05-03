package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {

    @Column(length = 100, nullable = false)
    private String phoneNumber;

    @Column(length = 500)
    private String notes;

    @OneToMany(targetEntity = Pet.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;
}
