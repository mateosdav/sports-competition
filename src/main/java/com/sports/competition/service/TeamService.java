package com.sports.competition.service;

import com.sports.competition.model.Team;
import com.sports.competition.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    public List<Team> findAll() {
        return teamRepository.findAll();
    }
    
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }
    
    public Team save(Team team) {
        return teamRepository.save(team);
    }
    
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
    
    public List<Team> findByCompetitionId(Long competitionId) {
        return teamRepository.findByCompetitionId(competitionId);
    }
    
    public boolean existsById(Long id) {
        return teamRepository.existsById(id);
    }
}