package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContratServiceImplGetChiffreAffaireTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDates() {
        // Arrange
        Date startDate = new Date(System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000)); // 1 month ago
        Date endDate = new Date();
        List<Contrat> contrats = new ArrayList<>();

        // Creating contracts with specialities
        Contrat contratIA = new Contrat();
        contratIA.setSpecialite(Specialite.IA);
        contratIA.setMontantContrat(300); // Setting a sample amount for IA contract
        contrats.add(contratIA);

        Contrat contratCloud = new Contrat();
        contratCloud.setSpecialite(Specialite.CLOUD);
        contratCloud.setMontantContrat(400); // Setting a sample amount for Cloud contract
        contrats.add(contratCloud);

        // Mocking the repository to return the list of contracts
        when(contratRepository.findAll()).thenReturn(contrats);

        // Act
        float chiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert
        float expectedChiffreAffaire = (300 + 400); // Total amount for 1 month of IA and 1 month of Cloud
        assertEquals(expectedChiffreAffaire, chiffreAffaire, 0.01); // Using delta for float comparison
        verify(contratRepository, times(1)).findAll(); // Verify that findAll was called once
    }
}
