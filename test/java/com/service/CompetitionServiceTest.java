package com.sports.competition.service;

import com.sports.competition.model.Competition;
import com.sports.competition.repository.CompetitionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {
    
    @Mock
    private CompetitionRepository competitionRepository;
    
    @InjectMocks
    private CompetitionService competitionService;
    
    @Test
    public void testFindAll() {
        // Given
        Competition comp1 = new Competition("Torneo 1", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        Competition comp2 = new Competition("Torneo 2", "Baloncesto", 
            LocalDate.now(), LocalDate.now().plusDays(15), 3);
        
        when(competitionRepository.findAll()).thenReturn(Arrays.asList(comp1, comp2));
        
        // When
        List<Competition> competitions = competitionService.findAll();
        
        // Then
        assertEquals(2, competitions.size());
        verify(competitionRepository, times(1)).findAll();
    }
    
    @Test
    public void testFindById() {
        // Given
        Competition competition = new Competition("Torneo", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        competition.setId(1L);
        
        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));
        
        // When
        Optional<Competition> found = competitionService.findById(1L);
        
        // Then
        assertTrue(found.isPresent());
        assertEquals("Torneo", found.get().getName());
        verify(competitionRepository, times(1)).findById(1L);
    }
    
    @Test
    public void testSave() {
        // Given
        Competition competition = new Competition("Nuevo Torneo", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);
        
        // When
        Competition saved = competitionService.save(competition);
        
        // Then
        assertNotNull(saved);
        assertEquals("Nuevo Torneo", saved.getName());
        verify(competitionRepository, times(1)).save(competition);
    }
}