package com.iteh.project.web;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predmet")
public class PredmetController {

    @Autowired
    private PredmetService predmetService;
    @GetMapping
    public ResponseEntity<?> findByName(@RequestParam(value = "predmet") String name) {
        Predmet predmet = predmetService.findByName(name);
        return ResponseEntity.ok(predmet);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Predmet predmet){
        Predmet predmet1 = predmetService.create(predmet);
        return ResponseEntity.ok(predmet1);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(predmetService.findAll());
    }
}
