package com.iteh.project.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iteh.project.domain.entity.PrijavaIspita;
import com.iteh.project.domain.entity.Profesor;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.models.PrijavaIspitaCreate;
import com.iteh.project.domain.models.PrijavaIspitaUpdate;
import com.iteh.project.domain.service.PrijavaIspitaService;
import com.iteh.project.domain.service.ProfesorService;
import com.iteh.project.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prijava-ispita")
public class PrijavaIspitaController {
    @Autowired
    private PrijavaIspitaService prijavaIspitaService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PrijavaIspitaCreate predmet, @AuthenticationPrincipal UserDetails userDetails) {
        Student student = studentService.findByBrojIndeksa(userDetails.getUsername());
        PrijavaIspita prijavaIspita = prijavaIspitaService.create(predmet, student);
        return ResponseEntity.ok(prijavaIspita);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody PrijavaIspitaUpdate prijavaIspitaUpdate) {

        PrijavaIspita prijavaIspita = prijavaIspitaService.update(prijavaIspitaUpdate);
        return ResponseEntity.ok(prijavaIspita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PrijavaIspita prijavaIspita = prijavaIspitaService.findById(id);
        return ResponseEntity.ok(prijavaIspita);
    }

    @GetMapping
    public ResponseEntity<List<PrijavaIspita>> findAll(@RequestParam(value = "student", required = false) String brIndeksa,
                                                       @RequestParam(value = "predmet", required = false) String predmet,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        Profesor profesor = profesorService.findProfesorByEmail(userDetails.getUsername());
        List<PrijavaIspita> prijaveIspita = prijavaIspitaService.findAll(brIndeksa, predmet, profesor);
        return ResponseEntity.ok(prijaveIspita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prijavaIspitaService.delete(id);
    }
}
