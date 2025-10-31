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
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Column(unique = true)
    @Email(message = "Email tem quer ser válido")
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @Column(name = "data_cadastro", updatable = false)
    @CreatedDate
    @NotBlank(message = "Data de cadastro não pode ser vazia")
    private LocalDateTime dataCadastro;

    @Column
    @NotBlank(message = "Telefone não pode ser vazio")
    private String telefone;

}
