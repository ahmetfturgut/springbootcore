package com.aft.monoproject.Spring.mono.project.repository;

import com.aft.monoproject.Spring.mono.project.entity.Auth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth, String> {
    Optional<Auth> findByToken(String token);


}