package com.sports.competition.controller;

import com.sports.competition.model.Competition;
import com.sports.competition.service.CompetitionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompetitionController.class)
public class CompetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompetitionService competitionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCompetitions() throws Exception {
        Competition comp1 = new Competition("Torneo 1", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        Competition comp2 = new Competition("Torneo 2", "Baloncesto", 
            LocalDate.now(), LocalDate.now().plusDays(15), 3);

        when(competitionService.findAll()).thenReturn(Arrays.asList(comp1, comp2));

        mockMvc.perform(get("/api/competitions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Torneo 1"))
                .andExpect(jsonPath("$[1].name").value("Torneo 2"));

        verify(competitionService, times(1)).findAll();
    }

    @Test
    public void testGetCompetitionById() throws Exception {
        Competition competition = new Competition("Torneo Test", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        competition.setId(1L);

        when(competitionService.findById(1L)).thenReturn(Optional.of(competition));

        mockMvc.perform(get("/api/competitions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Torneo Test"));

        verify(competitionService, times(1)).findById(1L);
    }
}