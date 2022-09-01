package com.iteh.project.web;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.service.PrijavaPredmetaService;
import com.iteh.project.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prijava-predmeta")
public class PrijavaPredmetaController {
    @Autowired
    private PrijavaPredmetaService prijavaPredmetaService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> findAllByStudentAndCurrentYear(@AuthenticationPrincipal UserDetails userDetails) {
        Student student = studentService.findByBrojIndeksa(userDetails.getUsername());
        List<Predmet> predmeti = prijavaPredmetaService.findAllByStudentAndCurrentYear(student);
        return ResponseEntity.ok(predmeti);
    }
}
