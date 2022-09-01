package com.iteh.project.domain.service;

import com.iteh.project.domain.constants.Constants;
import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.PrijavaPredmeta;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.models.PrijavaIspitaCreate;
import com.iteh.project.domain.repository.PrijavaPredmetaRepo;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PrijavaPredmetaService {
    @Autowired
    private PrijavaPredmetaRepo prijavaPredmetaRepo;
    @Autowired
    private PredmetService predmetService;

    public List<Predmet> findAllByStudentAndCurrentYear(Student student) {
        List<PrijavaPredmeta> prijavePredmeta =
                prijavaPredmetaRepo.findAllByStudentIdAndDateBetween(
                        student.getId(), Constants.DATUM_POCETKA_PRIJAVE, Constants.DATUM_ZAVRSETKA_PRIJAVE);
        List<Predmet> predmeti = prijavePredmeta.stream()
                .map(PrijavaPredmeta::getPredmet)
                .collect(Collectors.toList());
        log.info(predmeti.toString());
        return predmeti;
    }

    public PrijavaPredmeta create(PrijavaIspitaCreate predmet, Student student) {
        Predmet predmet1 = predmetService.findByName(predmet.getPredmet());
        List<PrijavaPredmeta> prijavePredmeta = prijavaPredmetaRepo.findAllByStudentIdAndDateBetween(student.getId(), Constants.DATUM_POCETKA_PRIJAVE, Constants.DATUM_ZAVRSETKA_PRIJAVE);
        for (PrijavaPredmeta pr : prijavePredmeta) {
            if (pr.getPredmet().equals(predmet1)) {
                throw new NotFound("Predmet " + predmet1.getNaziv() + " je vec prijavljen!");
            }
        }
        PrijavaPredmeta prijavaPredmeta = new PrijavaPredmeta();
        prijavaPredmeta.setStudent(student);
        prijavaPredmeta.setPredmet(predmet1);
        return prijavaPredmetaRepo.save(prijavaPredmeta);
    }
}
