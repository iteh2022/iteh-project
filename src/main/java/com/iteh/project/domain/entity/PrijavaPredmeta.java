package com.iteh.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaPredmeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    private LocalDate date;

    @Override
    public String toString() {
        return "PrijavaPredmeta{" +
                "id=" + id +
                ", student=" + student.getBrojIndeksa() +
                ", predmet=" + predmet.getNaziv() +
                ", date=" + date.toString() +

                '}';
    }

    @PrePersist
    private void prePersist() {
        this.date = LocalDate.now();
    }

}
