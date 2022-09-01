package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.repository.PredmetRepo;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredmetService {

    @Autowired
    private PredmetRepo predmetRepo;

    public Predmet findByName(String name) {
        return predmetRepo.findByNaziv(name).orElseThrow(() -> new NotFound("Predmet " + name + " ne postoji!"));
    }

    public Predmet create(Predmet predmet) {

        return predmetRepo.save(predmet);

    }
}
