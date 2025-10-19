package com.sports.competition.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;
    
    @ManyToOne
    @JoinColumn(name = "team_a_id", nullable = false)
    private Team teamA;
    
    @ManyToOne
    @JoinColumn(name = "team_b_id", nullable = false)
    private Team teamB;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(name = "track_number", nullable = false)
    private Integer trackNumber;
    
    public Match() {}
    
    public Match(Competition competition, Team teamA, Team teamB, LocalDateTime date, Integer trackNumber) {
        this.competition = competition;
        this.teamA = teamA;
        this.teamB = teamB;
        this.date = date;
        this.trackNumber = trackNumber;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Competition getCompetition() { return competition; }
    public void setCompetition(Competition competition) { this.competition = competition; }
    
    public Team getTeamA() { return teamA; }
    public void setTeamA(Team teamA) { this.teamA = teamA; }
    
    public Team getTeamB() { return teamB; }
    public void setTeamB(Team teamB) { this.teamB = teamB; }
    
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    
    public Integer getTrackNumber() { return trackNumber; }
    public void setTrackNumber(Integer trackNumber) { this.trackNumber = trackNumber; }
}