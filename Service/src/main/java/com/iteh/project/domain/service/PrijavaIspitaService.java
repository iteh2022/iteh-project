package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.PrijavaIspita;
import com.iteh.project.domain.entity.Profesor;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.repository.PrijavaIspitaRepo;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public PrijavaIspita update(Integer ocena, String brojIndeksa, String nazivPredmeta, String date) {
        LocalDate date1 = LocalDate.parse(date);
        Predmet predmet1 = predmetService.findByName(nazivPredmeta);
        Student student = studentService.findByBrojIndeksa(brojIndeksa);
        PrijavaIspita prijavaIspita = prijavaIspitaRepo.findByStudentIdAndPredmetIdAndDate(student.getId(),predmet1.getId(),date1)
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

    public List<PrijavaIspita> findAll(String brIndeksa, String predmet, Profesor profesor) {
        Set<Predmet> predmeti = profesor.getPredmeti();
        List<PrijavaIspita> prijaveIspita = new ArrayList<>();
        if (brIndeksa == null && predmet == null) {
            predmeti.forEach(predmet1 -> {
                prijaveIspita.addAll(prijavaIspitaRepo.findAllByPredmetId(predmet1.getId()));
            });
            return prijaveIspita;
        }

        if (brIndeksa == null && predmet != null) {
            Predmet predmet1 = predmetService.findByName(predmet);
            if(!predmeti.contains(predmet1)) {
                throw new NotFound("Uneti predmet nije na listi predmeta sa vase katedre!");
            }
            return prijavaIspitaRepo.findAllByPredmetId(predmet1.getId());
        }

        if (brIndeksa != null && predmet == null) {
            Student student = studentService.findByBrojIndeksa(brIndeksa);
            predmeti.forEach(predmet1 -> {
                prijaveIspita.addAll
                        (prijavaIspitaRepo.findAllByStudentIdAndPredmetId(student.getId(), predmet1.getId()));
            });
            return prijaveIspita;
        }

        Student student = studentService.findByBrojIndeksa(brIndeksa);
        Predmet predmet1 = predmetService.findByName(predmet);
        prijaveIspita.addAll(prijavaIspitaRepo.findAllByStudentIdAndPredmetId(student.getId(), predmet1.getId()));
        return prijaveIspita;

    }

}
