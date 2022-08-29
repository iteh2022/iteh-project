package com.iteh.project.domain.service;

import com.iteh.project.domain.constants.Constants;
import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.PrijavaPredmeta;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.repository.PrijavaPredmetaRepo;
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
}
