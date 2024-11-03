package tn.esprit.spring.kaddem;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

public class DepartementServiceImplMockTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private Departement d1, d2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        d1 = new Departement(1, "Informatique");
        d2 = new Departement(2, "Génie Logiciel");
    }

    @Test
    public void testAddDepartement() {
        when(departementRepository.save(any(Departement.class))).thenReturn(d1);
        Departement savedDepartement = departementService.addDepartement(d1);
        assertNotNull(savedDepartement);
        assertEquals("Informatique", savedDepartement.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(d1));
        Departement foundDepartement = departementService.retrieveDepartement(1);
        assertNotNull(foundDepartement);
        assertEquals(1, foundDepartement.getIdDepart());
    }

    @Test
    public void testRetrieveAllDepartements() {
        when(departementRepository.findAll()).thenReturn(Arrays.asList(d1, d2));
        List<Departement> departements = departementService.retrieveAllDepartements();
        assertEquals(2, departements.size());
    }

    @Test
    public void testDeleteDepartement() {
        // Arrange
        int idToDelete = 1;
        when(departementRepository.findById(idToDelete)).thenReturn(Optional.of(d1));
        doNothing().when(departementRepository).delete(d1);  // Modifie ici pour delete(d1)

        // Act
        departementService.deleteDepartement(idToDelete);

        // Assert
        verify(departementRepository, times(1)).delete(d1);  // Modifie ici pour vérifier delete(d1)
    }


    @Test
    public void testUpdateDepartement() {
        when(departementRepository.save(d1)).thenReturn(d1);
        Departement updatedDepartement = departementService.updateDepartement(d1);
        assertEquals("Informatique", updatedDepartement.getNomDepart());
    }
}