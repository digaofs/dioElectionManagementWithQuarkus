package domain;


import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface CandidateRepository {
    void save(List<Candidate> candidates);
    default void save(Candidate candidate){
        save(List.of(candidate));
    }
    List<Candidate> findAll();
}
