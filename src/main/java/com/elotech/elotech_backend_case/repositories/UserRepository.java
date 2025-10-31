package com.elotech.elotech_backend_case.repositories;

import com.elotech.elotech_backend_case.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> { }
