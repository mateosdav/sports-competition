package com.sports.competition.service;

import com.sports.competition.model.Match;
import com.sports.competition.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    public List<Match> findAll() {
        return matchRepository.findAll();
    }
    
    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }
    
    public Match save(Match match) {
        return matchRepository.save(match);
    }
    
    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }
    
    public List<Match> findByCompetitionId(Long competitionId) {
        return matchRepository.findByCompetitionId(competitionId);
    }
    
    public boolean existsById(Long id) {
        return matchRepository.existsById(id);
    }
}