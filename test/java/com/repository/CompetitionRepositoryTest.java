package com.sports.competition.repository;

import com.sports.competition.model.Competition;
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
public class CompetitionRepositoryTest {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Test
    public void testSaveAndFindCompetition() {
        // Given
        Competition competition = new Competition();
        competition.setName("Test Competition");
        competition.setSport("Fútbol");
        competition.setStartDate(LocalDate.now());
        competition.setEndDate(LocalDate.now().plusDays(10));
        competition.setTracksPerDay(4);

        // When
        Competition saved = competitionRepository.save(competition);
        Competition found = competitionRepository.findById(saved.getId()).orElse(null);

        // Then
        assertNotNull(found);
        assertEquals("Test Competition", found.getName());
        assertEquals("Fútbol", found.getSport());
        assertEquals(4, found.getTracksPerDay());
    }

    @Test
    public void testDeleteCompetition() {
        // Given
        Competition competition = new Competition();
        competition.setName("Test Competition");
        competition.setSport("Fútbol");
        competition.setStartDate(LocalDate.now());
        competition.setEndDate(LocalDate.now().plusDays(10));
        competition.setTracksPerDay(4);

        Competition saved = competitionRepository.save(competition);

        // When
        competitionRepository.deleteById(saved.getId());
        Competition found = competitionRepository.findById(saved.getId()).orElse(null);

        // Then
        assertNull(found);
    }

    @Test
    public void testFindAllCompetitions() {
        // Given
        Competition comp1 = new Competition("Comp 1", "Fútbol", LocalDate.now(), LocalDate.now().plusDays(5), 3);
        Competition comp2 = new Competition("Comp 2", "Baloncesto", LocalDate.now(), LocalDate.now().plusDays(7), 2);
        
        competitionRepository.save(comp1);
        competitionRepository.save(comp2);

        // When
        var competitions = competitionRepository.findAll();

        // Then
        assertEquals(2, competitions.size());
    }
}