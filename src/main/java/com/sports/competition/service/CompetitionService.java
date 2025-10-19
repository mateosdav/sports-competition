package com.sports.competition.service;

import com.sports.competition.model.Competition;
import com.sports.competition.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }
    
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }
    
    public Competition save(Competition competition) {
        return competitionRepository.save(competition);
    }
    
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }
    
    public boolean existsById(Long id) {
        return competitionRepository.existsById(id);
    }
}