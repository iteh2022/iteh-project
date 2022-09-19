package com.iteh.project.domain.repository;

import com.iteh.project.domain.entity.PrijavaPredmeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrijavaPredmetaRepo extends JpaRepository<PrijavaPredmeta, Long> {
    List<PrijavaPredmeta> findAllByStudentId(Long id);

    List<PrijavaPredmeta> findAllByStudentIdAndDateBetween(Long id, LocalDate datumPocetkaPrijave, LocalDate datumZavrsetkaPrijave);
}
