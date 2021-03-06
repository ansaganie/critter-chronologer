package com.udacity.jdnd.course3.critter.pet;

import lombok.ToString;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */

public class PetDTO {
    private Long petId;
    private PetType type;
    private String name;
    private Long ownerId;
    private LocalDate birthDate;
    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "petId=" + petId +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
