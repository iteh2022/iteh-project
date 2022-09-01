package com.iteh.project.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "studenti")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String prezime;
    private String srednjeIme;
    @Embedded
    private Adresa adresa;
    private String brojIndeksa;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<PrijavaPredmeta> prijavaPredmeta;
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<PrijavaIspita> prijavaIspita;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", srednjeIme='" + srednjeIme + '\'' +
                ", adresa=" + adresa +
                ", brojIndeksa='" + brojIndeksa + '\'' +
                ", prijavaPredmeta=" + prijavaPredmeta +
                ", prijavaIspita=" + prijavaIspita +
                '}';
    }
}
