package com.iteh.project.domain.service;

import com.iteh.project.domain.constants.Constants;
import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.PrijavaIspita;
import com.iteh.project.domain.entity.Profesor;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.models.PrijavaIspitaCreate;
import com.iteh.project.domain.models.PrijavaIspitaUpdate;
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


    public PrijavaIspita create(PrijavaIspitaCreate predmet, Student student) {
        Predmet predmet1 = predmetService.findByName(predmet.getPredmet());
        List<PrijavaIspita> prijave = prijavaIspitaRepo
                .findAllByStudentIdAndPredmetIdAndDateBetween(
                        student.getId(), predmet1.getId(), Constants.DATUM_POCETKA_PRIJAVE, Constants.DATUM_ZAVRSETKA_PRIJAVE);

        if (prijave.size() > 0) {
            throw new NotFound("Predmet je vec prijavljen");
        }
        log.info(student.toString());
        PrijavaIspita prijavaIspita = new PrijavaIspita();
        prijavaIspita.setStudent(student);
        prijavaIspita.setPredmet(predmet1);
        PrijavaIspita newPrijavaIspita = prijavaIspitaRepo.save(prijavaIspita);
        log.info(newPrijavaIspita.toString());
        return newPrijavaIspita;

    }

   /* public PrijavaIspita update(PrijavaIspitaUpdate model) {
        Integer ocena = model.getOcena();
        LocalDate date1 = LocalDate.parse(model.getDatum());
        Predmet predmet1 = predmetService.findByName(model.getNazivPredmeta());
        Student student = studentService.findByBrojIndeksa(model.getBrIndeksa());
        PrijavaIspita prijavaIspita = prijavaIspitaRepo.findByStudentIdAndPredmetIdAndDate(student.getId(),predmet1.getId(),date1)
                .orElseThrow(() -> new NotFound(
                        "Prijava ispita studenta " + model.getBrIndeksa() + " iz predmeta " + model.getNazivPredmeta() + " ne postoji"
                        )
                );
        prijavaIspita.setOcena(ocena);
        prijavaIspitaRepo.save(prijavaIspita);
        return prijavaIspita;
    }*/

    public PrijavaIspita update(PrijavaIspitaUpdate model) {
        Integer ocena = model.getOcena();
        Long id = model.getId();
        PrijavaIspita prijavaIspita = prijavaIspitaRepo.findById(id)
                .orElseThrow(() -> new NotFound("Ne postoji prijava ispita sa id = " + id));
        prijavaIspita.setOcena(ocena);
        return prijavaIspitaRepo.save(prijavaIspita);
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

    public void delete(Long id) {
        if (!prijavaIspitaRepo.existsById(id)) {
            throw new NotFound("Prijava ispita sa id = " + id + " ne postoji!");
        }
        prijavaIspitaRepo.deleteById(id);
    }

    public List<PrijavaIspita> findAllByStudentId(Long id) {
        return prijavaIspitaRepo.findAllByStudentId(id);
    }
}
