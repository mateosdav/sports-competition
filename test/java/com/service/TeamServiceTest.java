package com.sports.competition.service;

import com.sports.competition.model.Team;
import com.sports.competition.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    
    @Mock
    private TeamRepository teamRepository;
    
    @InjectMocks
    private TeamService teamService;
    
    @Test
    public void testFindAll() {
        // Given
        Team team1 = new Team("Team A", null);
        Team team2 = new Team("Team B", null);
        
        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));
        
        // When
        List<Team> teams = teamService.findAll();
        
        // Then
        assertEquals(2, teams.size());
        verify(teamRepository, times(1)).findAll();
    }
    
    @Test
    public void testFindById() {
        // Given
        Team team = new Team("Team A", null);
        team.setId(1L);
        
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        
        // When
        Optional<Team> found = teamService.findById(1L);
        
        // Then
        assertTrue(found.isPresent());
        assertEquals("Team A", found.get().getName());
        verify(teamRepository, times(1)).findById(1L);
    }
}