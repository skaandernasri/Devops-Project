package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDepartement() {
        // Arrange: créer un département fictif
        Departement departement = new Departement();
        departement.setNomDepart("Informatique");

        // Simuler le comportement du repository pour retourner le département ajouté
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Act: appeler la méthode addDepartement du service
        Departement result = departementService.addDepartement(departement);

        // Assert: vérifier que le département retourné correspond à celui ajouté
        assertEquals("Informatique", result.getNomDepart());
    }
}
