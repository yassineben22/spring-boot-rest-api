package com.example.demo.api.model;

import com.example.demo.api.dto.AppartementDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class Appartement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numero;
    private String adresse;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Appartement(Long id, Integer numero, String adresse, User user) {
        this.id = id;
        this.numero = numero;
        this.adresse = adresse;
        this.user = user;
    }

    public Appartement(){}

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
