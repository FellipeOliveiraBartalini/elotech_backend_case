package com.elotech.elotech_backend_case.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BookCreateDto {

    @NotBlank(message = "Titulo cannot be blank")
    private String titulo;

    @NotBlank(message = "Autor cannot be blank")
    private String autor;

    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotBlank(message = "Data de publicacao cannot be blank")
    private LocalDateTime dataPublicacao;

    @NotBlank(message = "Categoria cannot be blank")
    private List<Long> categorias;
}
