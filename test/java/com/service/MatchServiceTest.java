package com.sports.competition.service;

import com.sports.competition.model.Match;
import com.sports.competition.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {
    
    @Mock
    private MatchRepository matchRepository;
    
    @InjectMocks
    private MatchService matchService;
    
    @Test
    public void testFindAll() {
        // Given
        Match match1 = new Match();
        Match match2 = new Match();
        
        when(matchRepository.findAll()).thenReturn(Arrays.asList(match1, match2));
        
        // When
        List<Match> matches = matchService.findAll();
        
        // Then
        assertEquals(2, matches.size());
        verify(matchRepository, times(1)).findAll();
    }
    
    @Test
    public void testFindById() {
        // Given
        Match match = new Match();
        match.setId(1L);
        
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));
        
        // When
        Optional<Match> found = matchService.findById(1L);
        
        // Then
        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
        verify(matchRepository, times(1)).findById(1L);
    }
    
    @Test
    public void testSave() {
        // Given
        Match match = new Match();
        
        when(matchRepository.save(any(Match.class))).thenReturn(match);
        
        // When
        Match saved = matchService.save(match);
        
        // Then
        assertNotNull(saved);
        verify(matchRepository, times(1)).save(match);
    }
    
    @Test
    public void testFindByCompetitionId() {
        // Given
        Match match1 = new Match();
        Match match2 = new Match();
        
        when(matchRepository.findByCompetitionId(1L)).thenReturn(Arrays.asList(match1, match2));
        
        // When
        List<Match> matches = matchService.findByCompetitionId(1L);
        
        // Then
        assertEquals(2, matches.size());
        verify(matchRepository, times(1)).findByCompetitionId(1L);
    }
}