package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;


import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;


import java.util.*;

@ExtendWith(MockitoExtension .class)
 class EtudiantServiceImpMockTest {
    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    DepartementServiceImpl departementService;
    @InjectMocks
    EtudiantServiceImpl etudiantService;
    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Test
   void retrieveAllEtudiantsWithDepartements_MockTest() {
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
    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(etudiants, result);
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        Etudiant e = new Etudiant();
        when(etudiantRepository.save(e)).thenReturn(e);

        Etudiant result = etudiantService.addEtudiant(e);

        assertEquals(e, result);
        verify(etudiantRepository, times(1)).save(e);
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant e = new Etudiant();
        when(etudiantRepository.save(e)).thenReturn(e);

        Etudiant result = etudiantService.updateEtudiant(e);

        assertEquals(e, result);
        verify(etudiantRepository, times(1)).save(e);
    }

    @Test
    void testRetrieveEtudiant() {
        Integer id = 1;
        Etudiant e = new Etudiant();
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(e));

        Etudiant result = etudiantService.retrieveEtudiant(id);

        assertEquals(e, result);
        verify(etudiantRepository, times(1)).findById(id);
    }

    @Test
    void testRemoveEtudiant() {
        Integer id = 1;
        Etudiant e = new Etudiant();
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(e));

        etudiantService.removeEtudiant(id);

        verify(etudiantRepository, times(1)).delete(e);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        Integer etudiantId = 1;
        Integer departementId = 1;
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();

        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(etudiantId, departementId);

        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {
        Etudiant e = new Etudiant();
        Integer idContrat = 1;
        Integer idEquipe = 1;
        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();
        equipe.setEtudiants(new HashSet<>());

        when(contratRepository.findById(idContrat)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(equipe));

        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(e, idContrat, idEquipe);

        assertEquals(e, result);
        assertEquals(e, contrat.getEtudiant());
        assertTrue(equipe.getEtudiants().contains(e));
    }

    @Test
    void testGetEtudiantsByDepartement() {
        Integer idDepartement = 1;
        List<Etudiant> etudiants = new ArrayList<>();
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement)).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(idDepartement);

        assertEquals(etudiants, result);
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(idDepartement);
    }

    @Test
    void testRetrieveAllEtudiantsWithDepartements() {
        List<Etudiant> etudiants = new ArrayList<>();
        when(etudiantRepository.findAllEtudiantsWithDepartements()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiantsWithDepartements();

        assertEquals(etudiants, result);
        verify(etudiantRepository, times(1)).findAllEtudiantsWithDepartements();
    }
}
