package com.elotech.elotech_backend_case.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "Nome cannot be blank")
    private String nome;

    @Column
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column
    @CreatedDate
    @NotBlank(message = "Data de cadastro cannot be blank")
    private LocalDateTime data_cadastro;

    @Column
    @NotBlank(message = "Telefone cannot be blank")
    private String telefone;

}
