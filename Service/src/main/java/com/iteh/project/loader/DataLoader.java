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
    private ProfesorRepo profesorRepo;
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
        role.setName("ROLE_STUDENT");
        role = roleRepo.save(role);

        user.setRoles(Set.of(role));
        userRepo.save(user);

        User user1 = new User();
        user1.setUsername("profa");
        String password1 = bCryptPasswordEncoder.encode("tajnasifraa");
        user1.setPassword(password1);

        Role role1 = new Role();
        role1.setName("ROLE_PROFA");
        role1 = roleRepo.save(role1);

        user1.setRoles(Set.of(role1));
        userRepo.save(user1);

        User user2 = new User();
        user2.setUsername("20180418");
        String password8 = bCryptPasswordEncoder.encode("tajnasifra");
        user2.setPassword(password8);
        user2.setRoles(Set.of(role));
        userRepo.save(user2);


        Student novi = new Student();
        novi.setIme("Branko");
        novi.setBrojIndeksa("20180418");
        studentRepo.save(novi);
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

        Predmet predmet2 = new Predmet();
        predmet2.setNaziv("Matematika2");
        predmet2 = predmetRepo.save(predmet2);
        Profesor profa = new Profesor();
        profa.setEmail("profa");
        profa.setIme("Nikola");
        profa.setPrezime("Rusimovic");
        profa.setPredmeti(Set.of(predmet1,predmet2));
        profa = profesorRepo.save(profa);

        Predmet predmet3 = new Predmet();
        predmet3.setNaziv("Matematika3");
        predmet3 = predmetRepo.save(predmet3);



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

        PrijavaIspita prijavaIspita1 = new PrijavaIspita();
        prijavaIspita1.setStudent(student1);
        prijavaIspita1.setPredmet(predmet2);
        prijavaIspitaRepo.save(prijavaIspita1);

        PrijavaIspita prijavaIspita2 = new PrijavaIspita();
        prijavaIspita2.setStudent(student1);
        prijavaIspita2.setPredmet(predmet3);
        prijavaIspitaRepo.save(prijavaIspita2);
        List<PrijavaIspita> prijavaIspitaList = prijavaIspitaRepo.findAllByStudentId(student1.getId());
        System.out.println(prijavaIspitaList);

        Student studentToUpdate = studentRepo.findByBrojIndeksa(student1.getBrojIndeksa()).orElseThrow(() -> new NotFound(""));



        PrijavaPredmeta prijavaPredmeta = new PrijavaPredmeta();
        prijavaPredmeta.setPredmet(predmet1);
        prijavaPredmeta.setStudent(studentToUpdate);

        PrijavaPredmeta prijavaPredmeta1 = new PrijavaPredmeta();
        prijavaPredmeta1.setPredmet(predmet2);
        prijavaPredmeta1.setStudent(studentToUpdate);

        PrijavaPredmeta prijavaPredmeta2 = new PrijavaPredmeta();
        prijavaPredmeta2.setPredmet(predmet3);
        prijavaPredmeta2.setStudent(studentToUpdate);

        prijavaPredmetaRepo.save(prijavaPredmeta);
        prijavaPredmetaRepo.save(prijavaPredmeta1);
        prijavaPredmetaRepo.save(prijavaPredmeta2);
        System.out.println(studentToUpdate.getId());




    }
}
