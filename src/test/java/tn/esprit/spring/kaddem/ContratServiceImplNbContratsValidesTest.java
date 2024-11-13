package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Date;

public class ContratServiceImplNbContratsValidesTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testNbContratsValides() {
        // Arrange
        Date startDate = new Date(); // Use a specific date or mock it if needed
        Date endDate = new Date(); // Use a specific date or mock it if needed
        Integer expectedNbContratsValides = 5;

        // Mock the repository method
        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(expectedNbContratsValides);

        // Act
        Integer nbContratsValides = contratService.nbContratsValides(startDate, endDate);

        // Assert
        assertEquals(expectedNbContratsValides, nbContratsValides); // Verify the expected result
        verify(contratRepository, times(1)).getnbContratsValides(startDate, endDate); // Ensure the method was called once
    }
}