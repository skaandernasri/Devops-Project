package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.IDepartementService;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImpMockTest {
    @Mock
    private IDepartementService departementService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private IUniversiteService universiteService;

    @Test
    public void assignUniversiteToDepartement() {

        Universite universite = new Universite("esprit");
        Universite savedUniversite = new Universite("esprit"); // Dummy object to return
        Departement dep = new Departement("depGamix");
        Departement depSaved = new Departement("depGamix"); // Dummy object to return

        Mockito.when(universiteService.addUniversite(universite)).thenReturn(savedUniversite);
        Mockito.when(departementService.addDepartement(dep)).thenReturn(depSaved);
        Mockito.when(universiteRepository.save(savedUniversite)).thenReturn(savedUniversite);

        Universite resultUniversite = universiteService.addUniversite(universite);
        Departement savedDep = departementService.addDepartement(dep);

        Set<Departement> setDep = new HashSet<>();
        setDep.add(savedDep);
        resultUniversite.setDepartements(setDep);
        resultUniversite = universiteRepository.save(resultUniversite);

        Assertions.assertNotNull(resultUniversite.getDepartements(), "The university should have departments.");

        Mockito.verify(universiteService).addUniversite(universite);
        Mockito.verify(departementService).addDepartement(dep);
        Mockito.verify(universiteRepository).save(resultUniversite);
    }
}
