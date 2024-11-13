package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContratServiceImplRetrieveAndUpdateStatusTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testRetrieveAndUpdateStatusContrat() {
        // Arrange
        List<Contrat> contrats = new ArrayList<>();

        // Contrat that should remain unarchived
        Contrat contratNonArchive = new Contrat();
        contratNonArchive.setArchive(false);
        contratNonArchive.setDateFinContrat(new Date(System.currentTimeMillis() - (15L * 24 * 60 * 60 * 1000))); // Expired 15 days ago

        // Contrat that should be archived
        Contrat contratAArchiver = new Contrat();
        contratAArchiver.setArchive(false);
        contratAArchiver.setDateFinContrat(new Date(System.currentTimeMillis())); // Expired today

        contrats.add(contratNonArchive);
        contrats.add(contratAArchiver);

        when(contratRepository.findAll()).thenReturn(contrats); // Mocking the repository to return the list of contracts

        // Act
        contratService.retrieveAndUpdateStatusContrat(); // Call the method under test

        // Assert
        assertTrue(contratAArchiver.getArchive()); // Check if the contract was archived
        verify(contratRepository, times(1)).save(contratAArchiver); // Verify that save was called for the archived contract
        verify(contratRepository, never()).save(contratNonArchive); // Ensure that save was never called for the non-archived contract
    }
}
