package com.sports.competition.repository;

import com.sports.competition.model.Competition;
import com.sports.competition.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Test
    public void testSaveAndFindTeam() {
        // Given
        Competition competition = new Competition("Test Comp", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        Competition savedComp = competitionRepository.save(competition);

        Team team = new Team();
        team.setName("Test Team");
        team.setCompetition(savedComp);

        // When
        Team saved = teamRepository.save(team);
        Team found = teamRepository.findById(saved.getId()).orElse(null);

        // Then
        assertNotNull(found);
        assertEquals("Test Team", found.getName());
        assertEquals(savedComp.getId(), found.getCompetition().getId());
    }

    @Test
    public void testFindByCompetitionId() {
        // Given
        Competition competition = new Competition("Test Comp", "Fútbol", 
            LocalDate.now(), LocalDate.now().plusDays(10), 4);
        Competition savedComp = competitionRepository.save(competition);

        Team team1 = new Team("Team A", savedComp);
        Team team2 = new Team("Team B", savedComp);
        
        teamRepository.save(team1);
        teamRepository.save(team2);

        // When
        var teams = teamRepository.findByCompetitionId(savedComp.getId());

        // Then
        assertEquals(2, teams.size());
    }
}