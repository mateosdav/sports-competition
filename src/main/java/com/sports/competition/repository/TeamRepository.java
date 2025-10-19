package com.sports.competition.repository;

import com.sports.competition.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
    @Query("SELECT t FROM Team t WHERE t.competition.id = :competitionId")
    List<Team> findByCompetitionId(@Param("competitionId") Long competitionId);
}