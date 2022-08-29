package com.iteh.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prijava_ispita")
public class PrijavaIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "predmet_id")
    private Predmet predmet;

    private Integer ocena;


    @PrePersist
    private void prePersist() {
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "PrijavaIspita{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", student=" + student.getBrojIndeksa() +
                ", predmet=" + predmet.getNaziv() +
                '}';
    }
}
