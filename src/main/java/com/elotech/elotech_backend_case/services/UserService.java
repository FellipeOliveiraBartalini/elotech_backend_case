package com.elotech.elotech_backend_case.services;

import com.elotech.elotech_backend_case.dtos.UserCreateDto;
import com.elotech.elotech_backend_case.models.UserModel;
import com.elotech.elotech_backend_case.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel save(@Valid UserCreateDto userCreateDto) {
        UserModel userModel = new UserModel();
        userModel.setNome(userCreateDto.getNome());
        userModel.setEmail(userCreateDto.getEmail());
        userModel.setTelefone(userCreateDto.getTelefone());
        userModel.setDataCadastro(LocalDateTime.now());

        return userRepository.save(userModel);
    }

    public UserModel update(Long id, @Valid UserModel newUserModel) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setNome(newUserModel.getNome());
        existingUser.setEmail(newUserModel.getEmail());
        existingUser.setTelefone(newUserModel.getTelefone());

        return userRepository.save(existingUser);
    }

    public void delete(UserModel userModel) {
        userRepository.deleteById(userModel.getId());
    }
}
