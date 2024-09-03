package domain;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public interface CandidateRepository {
    void save(List<Candidate> candidates);

    default void save(Candidate candidate){
        save(List.of(candidate));
    }

    default List<Candidate> findAll(){
        return find(new CandidateQuery.Builder().build());
    };

    default Optional<Candidate> findById(String id){
        CandidateQuery query = new CandidateQuery.Builder().ids(Set.of(id)).build();

        return find(query).stream().findFirst();
    };

    List<Candidate> find(CandidateQuery query);
}
