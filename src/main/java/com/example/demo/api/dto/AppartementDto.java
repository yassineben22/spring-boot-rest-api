package com.example.demo.api.dto;

import com.example.demo.api.advice.validation.InsertValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public class AppartementDto {
    private Long id;
    @NotBlank(message = "le numero de l'appartement est obligatoire!", groups = InsertValidation.class)
    private Integer numero;
    @NotBlank(message = "l'adresse de l'appartement est obligatoire!", groups = InsertValidation.class)
    private String adresse;

    public AppartementDto(){}
    public AppartementDto(Long id, Integer numero, String adresse) {
        this.id = id;
        this.numero = numero;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "AppartementDto{" +
                "id=" + id +
                ", numero=" + numero +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
