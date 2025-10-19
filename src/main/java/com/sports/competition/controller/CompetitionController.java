package com.sports.competition.controller;

import com.sports.competition.model.Competition;
import com.sports.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    
    @Autowired
    private CompetitionService competitionService;
    
    @GetMapping
    public List<Competition> getAllCompetitions() {
        return competitionService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long id) {
        Optional<Competition> competition = competitionService.findById(id);
        return competition.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Competition createCompetition(@RequestBody Competition competition) {
        return competitionService.save(competition);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(@PathVariable Long id, @RequestBody Competition competitionDetails) {
        if (!competitionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        competitionDetails.setId(id);
        Competition updatedCompetition = competitionService.save(competitionDetails);
        return ResponseEntity.ok(updatedCompetition);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        if (!competitionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        competitionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}