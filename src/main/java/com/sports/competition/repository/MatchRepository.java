package com.sports.competition.repository;

import com.sports.competition.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    @Query("SELECT m FROM Match m WHERE m.competition.id = :competitionId")
    List<Match> findByCompetitionId(@Param("competitionId") Long competitionId);
}