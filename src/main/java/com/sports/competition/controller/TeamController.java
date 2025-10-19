package com.sports.competition.controller;

import com.sports.competition.model.Team;
import com.sports.competition.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamService.findById(id);
        return team.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/competition/{competitionId}")
    public List<Team> getTeamsByCompetition(@PathVariable Long competitionId) {
        return teamService.findByCompetitionId(competitionId);
    }
    
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.save(team);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team teamDetails) {
        Optional<Team> existingTeam = teamService.findById(id);
        if (existingTeam.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        teamDetails.setId(id);
        Team updatedTeam = teamService.save(teamDetails);
        return ResponseEntity.ok(updatedTeam);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        if (!teamService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}