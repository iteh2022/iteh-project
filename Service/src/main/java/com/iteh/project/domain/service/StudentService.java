package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.repository.StudentRepo;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student findByBrojIndeksa(String brIndeksa) {
        return studentRepo.findByBrojIndeksa(brIndeksa).orElseThrow(
                () -> new NotFound("Student sa indeksom " + brIndeksa + " ne postoji!"));
    }

    public Student create(Student student) {
        if (studentRepo.existsByBrojIndeksa(student.getBrojIndeksa())) {
            throw new NotFound("Student sa tim brojem indeksa vec postoji!");
        }
        return studentRepo.save(student);
    }
}
