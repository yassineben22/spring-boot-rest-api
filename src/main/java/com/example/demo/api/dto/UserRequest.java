package com.example.demo.api.dto;

import com.example.demo.api.advice.validation.InsertValidation;
import com.example.demo.api.advice.validation.UpdateValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

@Data
public class UserRequest {

    @NotBlank(message = "le nom est obligatoire!", groups = InsertValidation.class)
    private String name;

    @NotBlank(message="l'email est obligatoire!", groups = InsertValidation.class)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "l'email n'est pas valide!", groups = {UpdateValidation.class, InsertValidation.class})
    private String email;

}
