package com.iteh.project.domain.repository;

import com.iteh.project.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    Optional<Student> findByBrojIndeksa(String brojIndeksa);

    boolean existsByBrojIndeksa(String brojIndeksa);
}
