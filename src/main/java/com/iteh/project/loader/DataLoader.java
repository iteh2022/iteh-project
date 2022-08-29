package com.iteh.project.loader;

import com.iteh.project.domain.entity.*;
import com.iteh.project.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
    @Autowired
    private PredmetRepo predmetRepo;
    @Autowired
    private PrijavaPredmetaRepo prijavaPredmetaRepo;
    @Autowired
    private PrijavaIspitaRepo prijavaIspitaRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("20170418");
        String password = bCryptPasswordEncoder.encode("tajnasifra");
        user.setPassword(password);
        userRepo.save(user);


        Predmet predmet1 = new Predmet();
        Student student1 = new Student();
        PrijavaIspita prijavaIspita = new PrijavaIspita();
        Adresa adresa = new Adresa();

        adresa.setGrad("Smederevo");
        adresa.setBrojStana("4");
        adresa.setPostanskiBroj(11300);
        adresa.setUlicaIBroj("Sekspirova 1");

        predmet1.setNaziv("Matematika1");
        predmet1 = predmetRepo.save(predmet1);
        System.out.println("predmet 1 " + predmet1);


        student1.setAdresa(adresa);
        student1.setBrojIndeksa("20170418");
        student1.setIme("Nikola");
        student1.setPrezime("Rusimovic");
        student1.setSrednjeIme("Momcilo");
        studentRepo.save(student1);
        System.out.println("student 1 " + student1);

        prijavaIspita.setPredmet(predmet1);
        prijavaIspita.setStudent(student1);
        prijavaIspita = prijavaIspitaRepo.save(prijavaIspita);
        System.out.println(prijavaIspita);

        Student studentToUpdate = studentRepo.findByBrojIndeksa(student1.getBrojIndeksa());



        PrijavaPredmeta prijavaPredmeta = new PrijavaPredmeta();
        prijavaPredmeta.setPredmet(predmet1);
        prijavaPredmeta.setStudent(studentToUpdate);
        prijavaPredmetaRepo.save(prijavaPredmeta);
        studentToUpdate.getPrijavaPredmeta().add(prijavaPredmeta);
        studentRepo.save(studentToUpdate);
        prijavaPredmeta = prijavaPredmetaRepo.findByStudentId(studentToUpdate.getId());
        System.out.println(prijavaPredmeta);
        System.out.println("student to update = " + studentToUpdate);

        System.out.println(studentToUpdate.getPrijavaIspita());
        studentToUpdate.getPrijavaPredmeta().add(prijavaPredmeta);
        studentRepo.save(studentToUpdate);
        System.out.println(studentToUpdate);

    }
}
