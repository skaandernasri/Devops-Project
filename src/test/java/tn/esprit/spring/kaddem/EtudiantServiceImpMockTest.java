package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

@ExtendWith(MockitoExtension .class)
public class EtudiantServiceImpMockTest {
    @Mock
    IDepartementService departementService;
    @Mock
    IEtudiantService etudiantService;
    @Mock
    private EtudiantRepository etudiantRepository;
    @Test
    public void affecterEtudiantToDepartement() {
        Option opGamix = Option.GAMIX;
        Etudiant etudiant = new Etudiant("Nasri", "Skander", opGamix);
        Departement dep = new Departement("depGamix");
        Mockito.when(etudiantService.addEtudiant(etudiant)).thenReturn(etudiant);
        Mockito.when(departementService.addDepartement(dep)).thenReturn(dep);
        Mockito.when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);
        Departement depSaved = departementService.addDepartement(dep);
        savedEtudiant.setDepartement(depSaved);
        savedEtudiant = etudiantRepository.save(savedEtudiant);
        Assertions.assertNotNull(savedEtudiant.getDepartement(), "The student should be assigned to a department.");
        Assertions.assertEquals(depSaved.getIdDepart(), savedEtudiant.getDepartement().getIdDepart());
        departementService.deleteDepartement(depSaved.getIdDepart());
        etudiantRepository.delete(savedEtudiant);
        Mockito.verify(departementService).addDepartement(Mockito.any(Departement.class));
        Mockito.verify(etudiantService).addEtudiant(Mockito.any(Etudiant.class));
        Mockito.verify(etudiantRepository).delete(Mockito.any(Etudiant.class));
        Mockito.verify(etudiantRepository).save(Mockito.any(Etudiant.class));


    }
}
