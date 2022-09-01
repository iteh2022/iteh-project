package com.iteh.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresa {
    private String grad;
    private String ulicaIBroj;
    private String brojStana;
    private Integer postanskiBroj;
}
