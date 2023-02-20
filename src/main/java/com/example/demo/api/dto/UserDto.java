package com.example.demo.api.dto;

import com.example.demo.api.advice.validation.InsertValidation;
import com.example.demo.api.advice.validation.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class UserDto {
    private Long id;

    @NotBlank(message = "le nom est obligatoire!", groups = InsertValidation.class)
    private String name;

    @NotBlank(message="l'email est obligatoire!", groups = InsertValidation.class)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "l'email n'est pas valide!", groups = {UpdateValidation.class, InsertValidation.class})
    private String email;

    @NotEmpty(message = "Appartements obligatoires!", groups = {InsertValidation.class})
    private List<AppartementDto> appartements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AppartementDto> getAppartements() {
        return appartements;
    }

    public void setAppartements(List<AppartementDto> appartements) {
        this.appartements = appartements;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", appartements=" + appartements +
                '}';
    }
}
