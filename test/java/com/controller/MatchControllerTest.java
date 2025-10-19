package com.sports.competition.controller;

import com.sports.competition.model.Match;
import com.sports.competition.service.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MatchController.class)
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllMatches() throws Exception {
        Match match1 = new Match(null, null, null, LocalDateTime.now(), 1);
        Match match2 = new Match(null, null, null, LocalDateTime.now(), 2);

        when(matchService.findAll()).thenReturn(Arrays.asList(match1, match2));

        mockMvc.perform(get("/api/matches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(matchService, times(1)).findAll();
    }

    @Test
    public void testGetMatchesByCompetition() throws Exception {
        Match match1 = new Match(null, null, null, LocalDateTime.now(), 1);

        when(matchService.findByCompetitionId(1L)).thenReturn(Arrays.asList(match1));

        mockMvc.perform(get("/api/matches/competition/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(matchService, times(1)).findByCompetitionId(1L);
    }
}