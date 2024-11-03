package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

@SpringBootTest
public class DepartementServiceImplTest {

    @Autowired
    private IDepartementService departementService;

    @Test
    public void testAddDepartement() {
        // Arrange
        Departement d = new Departement("Informatique");

        // Act
        Departement savedDepartement = departementService.addDepartement(d);

        // Assert
        assertNotNull(savedDepartement);
        assertEquals("Informatique", savedDepartement.getNomDepart());
    }



    @Test
    public void testRetrieveDepartement() {
        // Arrange
        Departement d = new Departement("Test Retrieve");
        Departement savedDepartement = departementService.addDepartement(d);

        // Act
        Departement departement = departementService.retrieveDepartement(savedDepartement.getIdDepart());

        // Assert
        assertNotNull(departement);
        assertEquals("Test Retrieve", departement.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        // Arrange
        Departement d = new Departement("Génie Logiciel");
        Departement savedDepartement = departementService.addDepartement(d);
        savedDepartement.setNomDepart("Updated Departement");

        // Act
        Departement updatedDepartement = departementService.updateDepartement(savedDepartement);

        // Assert
        assertNotNull(updatedDepartement);
        assertEquals("Updated Departement", updatedDepartement.getNomDepart());
    }

    @Test
    public void testDeleteDepartement() {
        // Arrange
        Departement d = new Departement("Test Delete");
        Departement savedDepartement = departementService.addDepartement(d);
        int idToDelete = savedDepartement.getIdDepart();

        // Act
        departementService.deleteDepartement(idToDelete);

        // Assert
        assertThrows(NoSuchElementException.class, () -> {
            departementService.retrieveDepartement(idToDelete);
        }); // Vérifie que le département a bien été supprimé
    }
}
