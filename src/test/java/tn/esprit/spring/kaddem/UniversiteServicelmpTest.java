package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

@SpringBootTest
public class UniversiteServicelmpTest {

    @Autowired
    private IUniversiteService universiteService;

    @Test
    public void testAddUniversite() {
        // Arrange
        Universite u = new Universite("Université de Lyon");

        // Act
        Universite savedUniversite = universiteService.addUniversite(u);

        // Assert
        assertNotNull(savedUniversite);
        assertEquals("Université de Lyon", savedUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Act
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Assert
        assertNotNull(universites);
        assertTrue(universites.size() > 0, "No universities found");
    }

    @Test
    public void testRetrieveUniversite() {
        // Arrange
        Universite u = new Universite("Université de Lyon");
        Universite savedUniversite = universiteService.addUniversite(u);
        Integer id = savedUniversite.getIdUniv();

        // Act
        Universite universite = universiteService.retrieveUniversite(id);

        // Assert
        assertNotNull(universite);
        assertEquals(id, universite.getIdUniv());
    }

    @Test
    public void testUpdateUniversite() {
        // Arrange
        Universite u = new Universite("Université de Paris");
        Universite savedUniversite = universiteService.addUniversite(u);
        savedUniversite.setNomUniv("Université de Lyon");

        // Act
        Universite updatedUniversite = universiteService.updateUniversite(savedUniversite);

        // Assert
        assertNotNull(updatedUniversite);
        assertEquals("Université de Lyon", updatedUniversite.getNomUniv());
    }

    @Test
    public void testDeleteUniversite() {
        // Arrange
        Universite u = new Universite("Université de Lyon");
        Universite savedUniversite = universiteService.addUniversite(u);
        Integer idToDelete = savedUniversite.getIdUniv();

        // Act
        universiteService.deleteUniversite(idToDelete);

        // Assert
        assertNull(universiteService.retrieveUniversite(idToDelete), "The university was not deleted properly.");
    }
}
