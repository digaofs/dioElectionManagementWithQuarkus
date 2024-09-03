package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CandidateRepositoryTest {
    public abstract CandidateRepository candidateRepository();

    @Test
    void save(){
        Candidate candidate = Instancio.create(Candidate.class);
        candidateRepository().save(candidate);

        Optional<Candidate> result = candidateRepository().findById(candidate.id());

        assertTrue(result.isPresent());
        assertEquals(candidate, result.get());
    }

}