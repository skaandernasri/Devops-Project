package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private Departement departement1;
    private Departement departement2;

    @BeforeEach
    void setUp() {
        departement1 = new Departement(1, "Informatique");
        departement2 = new Departement(2, "Mathematiques");
    }


    @Test
    public void testAddDepartement() {
        when(departementRepository.save(departement1)).thenReturn(departement1);

        Departement savedDepartement = departementService.addDepartement(departement1);

        assertNotNull(savedDepartement);
        assertEquals("Informatique", savedDepartement.getNomDepart());
        verify(departementRepository, times(1)).save(departement1);
    }

    @Test
    public void testUpdateDepartement() {
        departement1.setNomDepart("Updated Informatique");
        when(departementRepository.save(departement1)).thenReturn(departement1);

        Departement updatedDepartement = departementService.updateDepartement(departement1);

        assertNotNull(updatedDepartement);
        assertEquals("Updated Informatique", updatedDepartement.getNomDepart());
        verify(departementRepository, times(1)).save(departement1);
    }

    @Test
    public void testRetrieveDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));

        Departement foundDepartement = departementService.retrieveDepartement(1);

        assertNotNull(foundDepartement);
        assertEquals(1, foundDepartement.getIdDepart());
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));
        doNothing().when(departementRepository).delete(departement1);

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).delete(departement1);
    }
}

