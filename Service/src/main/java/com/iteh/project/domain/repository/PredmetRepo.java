package com.iteh.project.domain.repository;

import com.iteh.project.domain.entity.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PredmetRepo extends JpaRepository<Predmet, Long> {
    Optional<Predmet> findByNaziv(String naziv);
}
