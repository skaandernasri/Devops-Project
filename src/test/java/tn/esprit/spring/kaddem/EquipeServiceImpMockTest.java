package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipeServiceImpMockTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Test
    public void testAddEquipe() {
        Equipe equipe = new Equipe("EquipeX");
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe savedEquipe = equipeService.addEquipe(equipe);

        verify(equipeRepository).save(equipe);
        //assertEquals("EquipeX", savedEquipe.getNom());
    }



    @Test
    public void testManageEtudiantsInEquipe() {
        // Création d'une nouvelle équipe
        Equipe equipe = new Equipe("Team AI");
        equipe.setEtudiants(new HashSet<>());  // Initialisation de l'ensemble des étudiants

        // Création d'un nouvel étudiant
        Etudiant etudiant1 = new Etudiant("John Doe", "Computer Science");

        // Ajout de l'étudiant à l'équipe
        equipe.getEtudiants().add(etudiant1);
        assertTrue(equipe.getEtudiants().contains(etudiant1), "L'étudiant doit être ajouté à l'équipe");

        // Suppression de l'étudiant de l'équipe
        equipe.getEtudiants().remove(etudiant1);
        assertFalse(equipe.getEtudiants().contains(etudiant1), "L'étudiant doit être retiré de l'équipe");
    }




    @Test
    public void testUpdateEquipe() {
        // Setup
        Equipe existingEquipe = new Equipe("EquipeY");
        existingEquipe.setIdEquipe(1);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(existingEquipe));

        Equipe updatedEquipe = new Equipe("UpdatedName");
        updatedEquipe.setIdEquipe(1);
        when(equipeRepository.save(any(Equipe.class))).thenReturn(updatedEquipe);

        // Action
        Equipe result = equipeService.updateEquipe(updatedEquipe);

        // Verification
        verify(equipeRepository).findById(1);
        verify(equipeRepository).save(any(Equipe.class));
        assertEquals("UpdatedName", result.getNomEquipe());
    }



}
