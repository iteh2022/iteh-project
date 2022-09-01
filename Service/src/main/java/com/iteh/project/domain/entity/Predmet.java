package com.iteh.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "premeti")
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;

    @JsonIgnore
    @OneToMany(mappedBy = "predmet")
    private Set<PrijavaPredmeta> prijavaPredmeta;

    @JsonIgnore
    @OneToMany(mappedBy = "predmet")
    private Set<PrijavaIspita> prijavaIspita;




}