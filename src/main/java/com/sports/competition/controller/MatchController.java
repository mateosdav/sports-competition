package com.sports.competition.controller;

import com.sports.competition.model.Match;
import com.sports.competition.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    
    @Autowired
    private MatchService matchService;
    
    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Optional<Match> match = matchService.findById(id);
        return match.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/competition/{competitionId}")
    public List<Match> getMatchesByCompetition(@PathVariable Long competitionId) {
        return matchService.findByCompetitionId(competitionId);
    }
    
    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchService.save(match);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match matchDetails) {
        Optional<Match> existingMatch = matchService.findById(id);
        if (existingMatch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        matchDetails.setId(id);
        Match updatedMatch = matchService.save(matchDetails);
        return ResponseEntity.ok(updatedMatch);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        if (!matchService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        matchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}