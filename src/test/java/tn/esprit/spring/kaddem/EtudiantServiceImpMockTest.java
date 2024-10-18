package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension .class)
public class EtudiantServiceImpMockTest {
    @InjectMocks
    DepartementServiceImpl departementService;
    @InjectMocks
    EtudiantServiceImpl etudiantService;
    @Mock
    private EtudiantRepository etudiantRepository;
    @Test
    public void retrieveAllEtudiantsWithDepartements_MockTest() {
        Option opGamix = Option.GAMIX;
        Departement dep = new Departement("depGamix");
        Etudiant etudiant1 = new Etudiant("Nasri", "Skander", opGamix);
        etudiant1.setDepartement(dep);
        Etudiant etudiant2 = new Etudiant("John", "Doe", Option.GAMIX);
        etudiant2.setDepartement(dep);
        etudiantService.addEtudiant(etudiant1);
        etudiantService.addEtudiant(etudiant2);
        when(etudiantRepository.findAllEtudiantsWithDepartements()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        List<Etudiant> etudiantsWithDepartements = etudiantService.retrieveAllEtudiantsWithDepartements();

        assertNotNull(etudiantsWithDepartements, "The list should not be null");
        assertEquals(2, etudiantsWithDepartements.size(), "Should retrieve two etudiants");

        for (Etudiant etudiant : etudiantsWithDepartements) {
            assertNotNull(etudiant.getDepartement(), "Each Etudiant should have a Departement assigned");
            assertEquals(dep.getNomDepart(), etudiant.getDepartement().getNomDepart(), "Departement names should match");
        }

        verify(etudiantRepository, times(1)).findAllEtudiantsWithDepartements();
    }
}
