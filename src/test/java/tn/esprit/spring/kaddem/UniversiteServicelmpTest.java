package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartementRestController.class)
public class DepartementRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepartementService departementService;

    private Departement departement1;
    private Departement departement2;

    @BeforeEach
    void setUp() {
        departement1 = new Departement(1, "Informatique");
        departement2 = new Departement(2, "Mathematiques");
    }

    @Test
    public void testGetDepartements() throws Exception {
        List<Departement> departementList = Arrays.asList(departement1, departement2);
        when(departementService.retrieveAllDepartements()).thenReturn(departementList);

        mockMvc.perform(get("/departement/retrieve-all-departements"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idDepart").value(1))
                .andExpect(jsonPath("$[0].nomDepart").value("Informatique"))
                .andExpect(jsonPath("$[1].idDepart").value(2))
                .andExpect(jsonPath("$[1].nomDepart").value("Mathematiques"));

        verify(departementService, times(1)).retrieveAllDepartements();
    }

    @Test
    public void testRetrieveDepartement() throws Exception {
        when(departementService.retrieveDepartement(1)).thenReturn(departement1);

        mockMvc.perform(get("/departement/retrieve-departement/{departement-id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idDepart").value(1))
                .andExpect(jsonPath("$.nomDepart").value("Informatique"));

        verify(departementService, times(1)).retrieveDepartement(1);
    }

    @Test
    public void testAddDepartement() throws Exception {
        when(departementService.addDepartement(any(Departement.class))).thenReturn(departement1);

        mockMvc.perform(post("/departement/add-departement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomDepart\":\"Informatique\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idDepart").value(1))
                .andExpect(jsonPath("$.nomDepart").value("Informatique"));

        verify(departementService, times(1)).addDepartement(any(Departement.class));
    }

    @Test
    public void testRemoveDepartement() throws Exception {
        doNothing().when(departementService).deleteDepartement(1);

        mockMvc.perform(delete("/departement/remove-departement/{departement-id}", 1))
                .andExpect(status().isOk());

        verify(departementService, times(1)).deleteDepartement(1);
    }


}

