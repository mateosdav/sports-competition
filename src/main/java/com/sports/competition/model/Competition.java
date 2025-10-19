package com.sports.competition.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String sport;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "tracks_per_day", nullable = false)
    private Integer tracksPerDay;
    
    public Competition() {}
    
    public Competition(String name, String sport, LocalDate startDate, LocalDate endDate, Integer tracksPerDay) {
        this.name = name;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tracksPerDay = tracksPerDay;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSport() { return sport; }
    public void setSport(String sport) { this.sport = sport; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public Integer getTracksPerDay() { return tracksPerDay; }
    public void setTracksPerDay(Integer tracksPerDay) { this.tracksPerDay = tracksPerDay; }
}