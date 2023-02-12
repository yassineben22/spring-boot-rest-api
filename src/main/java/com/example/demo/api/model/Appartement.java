package com.example.demo.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Appartement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numero;
    private String adresse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    public Appartement(Long id, Integer numero, String adresse) {
        this.id = id;
        this.numero = numero;
        this.adresse = adresse;
    }

    public Appartement() {
    }

}
