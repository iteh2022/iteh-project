package com.iteh.project.domain.repository;

import com.iteh.project.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    Student findByBrojIndeksa(String brojIndeksa);
}
