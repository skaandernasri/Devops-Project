package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        assertTrue(universites.size() > 0);
    }

    @Test
    public void testRetrieveUniversite() {
        // Act
        Universite universite = universiteService.retrieveUniversite(1);

        // Assert
        assertNotNull(universite);
        assertEquals(1, universite.getIdUniv());
    }

    @Test
    public void testUpdateUniversite() {
        // Arrange
        Universite u = new Universite(1, "Université de Lyon");

        // Act
        Universite updatedUniversite = universiteService.updateUniversite(u);

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
        Universite deletedUniversite = universiteService.retrieveUniversite(idToDelete);

        // Assert
        assertNull(deletedUniversite, "L'université n'a pas été supprimée correctement.");
    }


}
