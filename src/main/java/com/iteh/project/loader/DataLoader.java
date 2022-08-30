package com.iteh.project.loader;

import com.iteh.project.domain.entity.*;
import com.iteh.project.domain.repository.*;
import com.iteh.project.infrastructure.exceptions.custom.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
    private RoleRepo roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("20170418");
        String password = bCryptPasswordEncoder.encode("tajnasifra");
        user.setPassword(password);

        Role role = new Role();
        role.setName("STUDENT");
        role = roleRepo.save(role);

        user.setRoles(Set.of(role));
        userRepo.save(user);

        User user1 = new User();
        user1.setUsername("profa");
        String password1 = bCryptPasswordEncoder.encode("tajnasifraa");
        user1.setPassword(password1);

        Role role1 = new Role();
        role1.setName("PROFA");
        role1 = roleRepo.save(role1);

        user1.setRoles(Set.of(role1));
        userRepo.save(user1);


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
        List<PrijavaIspita> prijavaIspitaList = prijavaIspitaRepo.findAllByStudentId(student1.getId());
        System.out.println(prijavaIspitaList);

        Student studentToUpdate = studentRepo.findByBrojIndeksa(student1.getBrojIndeksa()).orElseThrow(() -> new NotFound(""));



        PrijavaPredmeta prijavaPredmeta = new PrijavaPredmeta();
        prijavaPredmeta.setPredmet(predmet1);
        prijavaPredmeta.setStudent(studentToUpdate);
        prijavaPredmetaRepo.save(prijavaPredmeta);
        System.out.println(studentToUpdate.getId());
        prijavaPredmeta = prijavaPredmetaRepo.findByStudentId(studentToUpdate.getId());
        System.out.println(prijavaPredmeta);



    }
}
