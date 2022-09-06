package com.iteh.project.web;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.PrijavaPredmeta;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.models.PrijavaIspitaCreate;
import com.iteh.project.domain.service.PrijavaPredmetaService;
import com.iteh.project.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/prijava-predmeta")
@CrossOrigin(origins = "http://localhost:5000/")
public class PrijavaPredmetaController {
    @Autowired
    private PrijavaPredmetaService prijavaPredmetaService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> findAllByStudentAndCurrentYear(Principal principal) {
        Student student = studentService.findByBrojIndeksa(principal.getName());
        List<Predmet> predmeti = prijavaPredmetaService.findAllByStudentAndCurrentYear(student);
        return ResponseEntity.ok(predmeti);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PrijavaIspitaCreate predmet,
                                    Principal principal) {
        Student student = studentService.findByBrojIndeksa(principal.getName());
        PrijavaPredmeta prijavaPredmeta1 = prijavaPredmetaService.create(predmet,student);
        return ResponseEntity.ok(prijavaPredmeta1);
    }
}
