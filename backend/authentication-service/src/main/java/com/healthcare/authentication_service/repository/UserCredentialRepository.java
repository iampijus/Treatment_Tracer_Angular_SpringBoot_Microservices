package com.healthcare.authentication_service.repository;

import com.healthcare.authentication_service.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    UserCredential findByEmail(String email);
}
