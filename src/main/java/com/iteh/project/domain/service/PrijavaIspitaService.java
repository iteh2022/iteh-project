package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.PrijavaIspita;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.repository.PrijavaIspitaRepo;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PrijavaIspitaService {

    @Autowired
    private PredmetService predmetService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PrijavaIspitaRepo prijavaIspitaRepo;


    public PrijavaIspita create(String predmet, Student student) {
        Predmet predmet1 = predmetService.findByName(predmet);

        log.info(student.toString());
        PrijavaIspita prijavaIspita = new PrijavaIspita();
        prijavaIspita.setStudent(student);
        prijavaIspita.setPredmet(predmet1);
        PrijavaIspita newPrijavaIspita = prijavaIspitaRepo.save(prijavaIspita);
        log.info(newPrijavaIspita.toString());
        return newPrijavaIspita;

    }

    public PrijavaIspita update(Integer ocena, String brojIndeksa, String nazivPredmeta) {
        Predmet predmet1 = predmetService.findByName(nazivPredmeta);
        Student student = studentService.findByBrojIndeksa(brojIndeksa);
        PrijavaIspita prijavaIspita = prijavaIspitaRepo.findByStudentIdAndPredmetId(student.getId(),predmet1.getId())
                .orElseThrow(() -> new NotFound(
                        "Prijava ispita studenta " + brojIndeksa + " iz predmeta " + nazivPredmeta + " ne postoji"
                        )
                );
        prijavaIspita.setOcena(ocena);
        prijavaIspitaRepo.save(prijavaIspita);
        return prijavaIspita;
    }

    public PrijavaIspita findById(Long id) {
        return prijavaIspitaRepo.findById(id).orElseThrow();
    }

    public List<PrijavaIspita> findAll(String brIndeksa, String predmet) {

        if (brIndeksa == null && predmet == null) {
            return prijavaIspitaRepo.findAll();
        }

        if (brIndeksa == null && predmet != null) {
            Predmet predmet1 = predmetService.findByName(predmet);
            return prijavaIspitaRepo.findAllByPredmetId(predmet1.getId());
        }

        if (brIndeksa != null && predmet == null) {
            Student student = studentService.findByBrojIndeksa(brIndeksa);
            return prijavaIspitaRepo.findAllByStudentId(student.getId());
        }

        Student student = studentService.findByBrojIndeksa(brIndeksa);
        Predmet predmet1 = predmetService.findByName(predmet);
        return List.of(prijavaIspitaRepo.findByStudentIdAndPredmetId(student.getId(), predmet1.getId())
                .orElseThrow(() -> new NotFound(
                        "Prijava ispita studenta " + brIndeksa + " iz predmeta " + predmet + " ne postoji!"
                        )
                ));

    }

}
