package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.HashSet;

public class ContratServiceImplAffectTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testAffectContratToEtudiant() {
        // Arrange
        Integer idContrat = 1;
        String nomE = "Doe";
        String prenomE = "John";

        // Create an Etudiant and Contrat
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE(nomE);
        etudiant.setPrenomE(prenomE);
        etudiant.setContrats(new HashSet<>());

        Contrat contrat = new Contrat();
        contrat.setIdContrat(idContrat);
        contrat.setArchive(false);

        // Mock repository responses
        when(etudiantRepository.findByNomEAndPrenomE(nomE, prenomE)).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(idContrat)).thenReturn(contrat);

        // Act
        Contrat affectedContrat = contratService.affectContratToEtudiant(idContrat, nomE, prenomE);

        // Assert
        assertNotNull(affectedContrat); // Check that the affected contract is not null
        assertEquals(etudiant, affectedContrat.getEtudiant()); // Verify that the contract is linked to the correct student
        verify(contratRepository, times(1)).save(contrat); // Ensure the save method is called once
    }
}
