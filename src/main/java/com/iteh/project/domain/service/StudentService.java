package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student findByBrojIndeksa(String brIndeksa) {
        return studentRepo.findByBrojIndeksa(brIndeksa);
    }
}
