package com.sports.competition.repository;

import com.sports.competition.model.Competition;
import com.sports.competition.model.Match;
import com.sports.competition.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class MatchRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testSaveAndFindMatch() {
        // Given
        Competition competition = new Competition("Test Comp", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        Competition savedComp = competitionRepository.save(competition);

        Team teamA = new Team("Team A", savedComp);
        Team teamB = new Team("Team B", savedComp);
        Team savedTeamA = teamRepository.save(teamA);
        Team savedTeamB = teamRepository.save(teamB);

        Match match = new Match();
        match.setCompetition(savedComp);
        match.setTeamA(savedTeamA);
        match.setTeamB(savedTeamB);
        match.setDate(LocalDateTime.now());
        match.setTrackNumber(1);

        // When
        Match saved = matchRepository.save(match);
        Match found = matchRepository.findById(saved.getId()).orElse(null);

        // Then
        assertNotNull(found);
        assertEquals(savedTeamA.getId(), found.getTeamA().getId());
        assertEquals(savedTeamB.getId(), found.getTeamB().getId());
        assertEquals(1, found.getTrackNumber());
    }

    @Test
    public void testFindByCompetitionId() {
        // Given
        Competition competition = new Competition("Test Comp", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        Competition savedComp = competitionRepository.save(competition);

        Team teamA = new Team("Team A", savedComp);
        Team teamB = new Team("Team B", savedComp);
        Team savedTeamA = teamRepository.save(teamA);
        Team savedTeamB = teamRepository.save(teamB);

        Match match = new Match(savedComp, savedTeamA, savedTeamB, LocalDateTime.now(), 1);
        matchRepository.save(match);

        // When
        var matches = matchRepository.findByCompetitionId(savedComp.getId());

        // Then
        assertEquals(1, matches.size());
    }
}