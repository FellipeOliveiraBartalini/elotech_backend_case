package com.elotech.elotech_backend_case.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "Titulo cannot be blank")
    private String titulo;

    @Column
    @NotBlank(message = "Autor cannot be blank")
    private String autor;

    @Column
    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @Column(name = "data_publicacao")
    @NotBlank(message = "Data de publicacao cannot be blank")
    private LocalDateTime dataPublicacao;

    @Column
    @NotBlank(message = "Categoria cannot be blank")
    private String categoria;
}
