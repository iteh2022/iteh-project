package com.iteh.project.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaIspitaUpdate {
    @JsonProperty("student")
    @Size(min = 8)
    private String brIndeksa;
    @JsonProperty("predmet")
    private String nazivPredmeta;
    @Min(5)
    @Max(10)
    private Integer ocena;
    private String datum;
}
