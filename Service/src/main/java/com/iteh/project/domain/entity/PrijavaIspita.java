package com.iteh.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prijave_ispita")
public class PrijavaIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    private Integer ocena;


    @PrePersist
    private void prePersist() {
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "PrijavaIspita{" +
                "id=" + id +
                ", date=" + date.toString() +
                ", student=" + student.getBrojIndeksa() +
                ", predmet=" + predmet.getNaziv() +
                '}';
    }
}
