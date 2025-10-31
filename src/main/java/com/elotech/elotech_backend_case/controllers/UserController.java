package com.elotech.elotech_backend_case.controllers;

import com.elotech.elotech_backend_case.dtos.UserCreateDto;
import com.elotech.elotech_backend_case.models.UserModel;
import com.elotech.elotech_backend_case.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public UserModel createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @Valid @RequestBody UserModel userModel) {
        return userService.update(id, userModel);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        UserModel user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userService.delete(user);
    }
}
