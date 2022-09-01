package com.iteh.project.domain.repository;

import com.iteh.project.domain.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepo extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByEmail(String email);
}
