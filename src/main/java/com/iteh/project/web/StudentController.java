package com.iteh.project.web;

import com.iteh.project.domain.entity.Student;
import com.iteh.project.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public ResponseEntity<?> create(@RequestBody Student student){
        Student student1 = studentService.create(student);

        return new ResponseEntity<>(student1, HttpStatus.CREATED);

    }

    public ResponseEntity<?> findByBrojIndeksa(String brojIndeksa){

        Student student = studentService.findByBrojIndeksa(brojIndeksa);

        return ResponseEntity.ok(student);
    }

}
