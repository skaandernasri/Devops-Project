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
import java.util.List;

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
    public void retrieveAllEtudiantsWithDepartements() {
        Option opGamix = Option.GAMIX;
        Etudiant etudiant1 = new Etudiant("Nasri", "Skander", opGamix);
        Etudiant etudiant2 = new Etudiant("John", "Doe", opGamix);
        Departement dep = new Departement("depGamix");

        etudiantService.addEtudiant(etudiant1);
        etudiantService.addEtudiant(etudiant2);
        Departement depSaved = departementService.addDepartement(dep);

        etudiant1.setDepartement(depSaved);
        etudiant2.setDepartement(depSaved);
        etudiantRepository.save(etudiant1);
        etudiantRepository.save(etudiant2);

        List<Etudiant> etudiantsWithDepartements = etudiantService.retrieveAllEtudiantsWithDepartements();

        Assertions.assertNotNull(etudiantsWithDepartements, "The list should not be null");
        Assertions.assertFalse(etudiantsWithDepartements.isEmpty(), "The list should not be empty");
        Assertions.assertEquals(2, etudiantsWithDepartements.size(), "Should retrieve two etudiants");

        for (Etudiant etudiant : etudiantsWithDepartements) {
            Assertions.assertNotNull(etudiant.getDepartement(), "Each Etudiant should have a Departement assigned");
            Assertions.assertEquals(depSaved.getIdDepart(), etudiant.getDepartement().getIdDepart(), "Departement IDs should match");
        }

        etudiantRepository.delete(etudiant1);
        etudiantRepository.delete(etudiant2);
        departementService.deleteDepartement(depSaved.getIdDepart());
    }





}
