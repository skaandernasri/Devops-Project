package tn.esprit.spring.kaddem;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

public class UniversiteServiceImpMockTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite u1, u2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        u1 = new Universite(1, "Université de Paris");
        u2 = new Universite(2, "Université de Tunis");
    }

    @Test
    public void testAddUniversite() {
        when(universiteRepository.save(any(Universite.class))).thenReturn(u1);
        Universite savedUniversite = universiteService.addUniversite(u1);
        assertNotNull(savedUniversite);
        assertEquals("Université de Paris", savedUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(u1));
        Universite foundUniversite = universiteService.retrieveUniversite(1);
        assertNotNull(foundUniversite);
        assertEquals(1, foundUniversite.getIdUniv());
    }

    @Test
    public void testGetAllUniversites() {
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(u1, u2));
        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertEquals(2, universites.size());
    }

    @Test
    public void testDeleteUniversite() {
        // Créez une université simulée
        Universite u = new Universite();
        u.setId(1);

        // Mocker le comportement du repository pour retourner cette université
        when(universiteRepository.findById(1)).thenReturn(Optional.of(u));

        // Appeler la méthode de suppression
        universiteService.deleteUniversite(1);

        // Vérifier que la méthode deleteById a bien été appelée
        verify(universiteRepository, times(1)).delete(u);
    }

    @Test
    public void testUpdateUniversite() {
        when(universiteRepository.save(u1)).thenReturn(u1);
        Universite updatedUniversite = universiteService.updateUniversite(u1);
        assertEquals("Université de Paris", updatedUniversite.getNomUniv());
    }
}