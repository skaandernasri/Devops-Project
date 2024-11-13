package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllDepartements() {
        // Arrange
        Departement departement1 = new Departement(1, "IT");
        Departement departement2 = new Departement(2, "Finance");
        List<Departement> departementList = Arrays.asList(departement1, departement2);

        when(departementRepository.findAll()).thenReturn(departementList);

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        // Arrange
        Departement departement = new Departement(1, "HR");
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Act
        Departement result = departementService.addDepartement(departement);

        // Assert
        assertNotNull(result);
        assertEquals("HR", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testUpdateDepartement() {
        // Arrange
        Departement departement = new Departement(1, "Marketing");
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Act
        Departement result = departementService.updateDepartement(departement);

        // Assert
        assertNotNull(result);
        assertEquals("Marketing", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testRetrieveDepartement() {
        // Arrange
        Departement departement = new Departement(1, "Sales");
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));

        // Act
        Departement result = departementService.retrieveDepartement(1);

        // Assert
        assertNotNull(result);
        assertEquals("Sales", result.getNomDepart());
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    void testRetrieveDepartement_NotFound() {
        // Arrange
        when(departementRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> departementService.retrieveDepartement(1));
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteDepartement() {
        // Arrange
        Departement departement = new Departement(1, "Engineering");
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));

        // Act
        departementService.deleteDepartement(1);

        // Assert
        verify(departementRepository, times(1)).delete(departement);
    }
}   
