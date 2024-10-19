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

public class ContratServiceImplAddTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contrat = new Contrat();
        contrat.setIdContrat(1);
    }

    @Test
    public void testAddContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.addContrat(contrat);
        assertEquals(contrat.getIdContrat(), result.getIdContrat());
        verify(contratRepository, times(1)).save(contrat);
    }
}
