package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

public class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat1;
    private Contrat contrat2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat2 = new Contrat();
        contrat2.setIdContrat(2);
    }

    @Test
    public void testRetrieveAllContrats() {
        List<Contrat> contrats = Arrays.asList(contrat1, contrat2);
        when(contratRepository.findAll()).thenReturn(contrats);

        List<Contrat> result = contratService.retrieveAllContrats();
        assertEquals(2, result.size());
        assertEquals(contrat1.getIdContrat(), result.get(0).getIdContrat());
    }
}
