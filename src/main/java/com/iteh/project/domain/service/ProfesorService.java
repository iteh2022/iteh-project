package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Profesor;
import com.iteh.project.domain.repository.ProfesorRepo;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepo profesorRepo;

    public Profesor findProfesorByEmail(String email) {
        return profesorRepo.findByEmail(email).orElseThrow(
                () -> new NotFound("Profesor sa email-om " + email + " ne postoji!")
        );
    }
}
