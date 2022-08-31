package com.iteh.project.web;

import com.iteh.project.domain.entity.Predmet;
import com.iteh.project.domain.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    public ResponseEntity<?> findByName(@RequestParam String name){

        Predmet predmet = predmetService.findByName(name);

        return ResponseEntity.ok(predmet);

    }

    public ResponseEntity<?> create(@RequestBody Predmet predmet){

        Predmet predmet1 = predmetService.create(predmet);

        return ResponseEntity.ok(predmet1);

    }
}
