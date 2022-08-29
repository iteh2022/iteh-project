package com.iteh.project.domain.repository;

import com.iteh.project.domain.entity.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrijavaIspitaRepo extends JpaRepository<PrijavaIspita, Long> {
    Optional<PrijavaIspita> findByStudentIdAndPredmetId(Long id, Long id1);

    List<PrijavaIspita> findAllByPredmetId(Long id);

    List<PrijavaIspita> findAllByStudentId(Long id);
}
