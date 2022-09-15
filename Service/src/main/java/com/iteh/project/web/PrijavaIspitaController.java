package com.iteh.project.web;


import com.iteh.project.domain.entity.PrijavaIspita;
import com.iteh.project.domain.entity.Profesor;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.models.PrijavaIspitaCreate;
import com.iteh.project.domain.models.PrijavaIspitaUpdate;
import com.iteh.project.domain.service.PrijavaIspitaService;
import com.iteh.project.domain.service.ProfesorService;
import com.iteh.project.domain.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
@Slf4j
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
    public ResponseEntity<?> create(@RequestBody PrijavaIspitaCreate predmet, Principal principal) {
        Student student = studentService.findByBrojIndeksa(principal.getName());
        PrijavaIspita prijavaIspita = prijavaIspitaService.create(predmet, student);
        return ResponseEntity.ok(prijavaIspita);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody PrijavaIspitaUpdate prijavaIspitaUpdate) {
        PrijavaIspita prijavaIspita = prijavaIspitaService.update(prijavaIspitaUpdate);
        return ResponseEntity.ok(prijavaIspita);
    }

    @GetMapping("/student")
    public ResponseEntity<?> findAllByStudentId(Principal principal) {
        Student student = studentService.findByBrojIndeksa(principal.getName());
        List<PrijavaIspita> prijavaIspita = prijavaIspitaService.findAllByStudentId(student.getId());
        return ResponseEntity.ok(prijavaIspita);
    }

    @GetMapping
    public ResponseEntity<List<PrijavaIspita>> findAll(@RequestParam(value = "student", required = false) String brIndeksa,
                                                       @RequestParam(value = "predmet", required = false) String predmet,
                                                       Principal principal) {
        Profesor profesor = profesorService.findProfesorByEmail(principal.getName());
        List<PrijavaIspita> prijaveIspita = prijavaIspitaService.findAll(brIndeksa, predmet, profesor);
        return ResponseEntity.ok(prijaveIspita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prijavaIspitaService.delete(id);
    }
}
