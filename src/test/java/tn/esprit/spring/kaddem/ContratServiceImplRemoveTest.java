package tn.esprit.spring.kaddem;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Optional;

public class ContratServiceImplRemoveTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        // Use openMocks as initMocks is deprecated
        MockitoAnnotations.openMocks(this);

        // Initialize the contract object
        contrat = new Contrat();
        contrat.setIdContrat(1);
    }

    @Test
    public void testRemoveContrat() {
        // Ensure the repository returns the contract wrapped in Optional
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        // Call the service method
        contratService.removeContrat(1);

        // Verify that delete method was called with the correct contract
        verify(contratRepository, times(1)).delete(contrat);
    }
}
