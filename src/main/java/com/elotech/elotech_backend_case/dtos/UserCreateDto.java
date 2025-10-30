package com.elotech.elotech_backend_case.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserCreateDto {

    @NotBlank
    @Min(value = 3)
    private String nome;

    @Email
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio")
    private String telefone;
}
