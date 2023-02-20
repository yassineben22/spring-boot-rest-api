package com.example.demo.api.model;

import com.example.demo.api.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Appartement> appartements;

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

    public List<Appartement> getAppartements() {
        return appartements;
    }

    public void setAppartements(List<Appartement> appartements) {
        this.appartements = appartements;
    }

}