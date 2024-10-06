package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EtudiantServiceImpTest {
    @Autowired
    IDepartementService departementService;
    @Autowired
    IEtudiantService etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Test
    public void affecterEtudiantToDepartement(){
        Option opGamix= Option.GAMIX;
        Etudiant etudiant = new Etudiant("Nasri","Skander",opGamix);
        Etudiant  savedEtudiant = etudiantService.addEtudiant(etudiant);
        Departement dep=new Departement("depGamix");
        Departement depSaved= departementService.addDepartement(dep);
        savedEtudiant.setDepartement(depSaved);
        savedEtudiant=etudiantRepository.save(savedEtudiant);
        Assertions.assertNotNull(savedEtudiant.getDepartement());
        Assertions.assertEquals(depSaved.getIdDepart(),savedEtudiant.getDepartement().getIdDepart());
        departementService.deleteDepartement(depSaved.getIdDepart());
        etudiantRepository.delete(savedEtudiant);
    }




}
