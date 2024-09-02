package infrastructure.repositories;

import domain.Candidate;
import domain.CandidateRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SQLCandidateRepository implements CandidateRepository {

    @Override
    public void save(List<Candidate> candidates) {

    }

    @Override
    public List<Candidate> findAll() {
        return List.of();
    }
}
