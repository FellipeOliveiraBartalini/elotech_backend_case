package com.elotech.elotech_backend_case.controllers;

import com.elotech.elotech_backend_case.dtos.LoanCreateDto;
import com.elotech.elotech_backend_case.models.LoanModel;
import com.elotech.elotech_backend_case.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanModel> getAllLoans() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public LoanModel getLoanById(@PathVariable Long id) {
        return loanService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public LoanModel create(@Valid @RequestBody LoanCreateDto loanCreateDto) {
        return loanService.create(loanCreateDto);
    }

    @PostMapping("/return-book/{id}")
    public LoanModel returnBook(@PathVariable Long id) {
        return loanService.returnBook(id);
    }

    @PostMapping("/renew/{id}")
    public LoanModel renew(@PathVariable Long id) {
        return loanService.renew(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }


}
