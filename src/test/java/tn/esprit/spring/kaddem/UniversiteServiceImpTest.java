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
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.services.IEtudiantService;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UniversiteServiceImpTest {
    @Autowired
    IDepartementService departementService;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    IUniversiteService universiteService;
    @Autowired
    private UniversiteRepository universiteRepository;
    @Test
    public void assignUniversiteToDepartement(){
        Universite universite = new Universite("esprit");
        Universite  savedUniversite = universiteService.addUniversite(universite);
        Departement dep=new Departement("depGamix");
        Departement depSaved= departementService.addDepartement(dep);
        Set<Departement> setDep=new HashSet<>();
        setDep.add(depSaved);
        savedUniversite.setDepartements(setDep);
        savedUniversite=universiteRepository.save(savedUniversite);
        Assertions.assertNotNull(savedUniversite.getDepartements());
        //Assertions.assertTrue(savedUniversite.getDepartements().contains(depSaved));
        departementService.deleteDepartement(depSaved.getIdDepart());
        universiteRepository.delete(savedUniversite);
    }

}
