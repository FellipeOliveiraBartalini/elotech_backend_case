package com.elotech.elotech_backend_case.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanCreateDto {

    @NotBlank
    private Long userId;

    @NotBlank
    private Long bookId;
}
