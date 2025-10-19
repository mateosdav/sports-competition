package com.sports.competition.controller;

import com.sports.competition.model.Team;
import com.sports.competition.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllTeams() throws Exception {
        Team team1 = new Team("Team A", null);
        Team team2 = new Team("Team B", null);

        when(teamService.findAll()).thenReturn(Arrays.asList(team1, team2));

        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Team A"))
                .andExpect(jsonPath("$[1].name").value("Team B"));

        verify(teamService, times(1)).findAll();
    }

    @Test
    public void testGetTeamsByCompetition() throws Exception {
        Team team1 = new Team("Team A", null);
        Team team2 = new Team("Team B", null);

        when(teamService.findByCompetitionId(1L)).thenReturn(Arrays.asList(team1, team2));

        mockMvc.perform(get("/api/teams/competition/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(teamService, times(1)).findByCompetitionId(1L);
    }
}