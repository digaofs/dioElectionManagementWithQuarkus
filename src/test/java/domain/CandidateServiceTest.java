package domain;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@QuarkusTest
class CandidateServiceTest {

    @Inject
    CandidateService candidateService;

    @InjectMock
    CandidateRepository repositoryMock;

    @Test
    void save(){
        Candidate candidate = Instancio.create(Candidate.class);

        candidateService.save(candidate);
        verify(repositoryMock).save(candidate);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    void findAll(){
        candidateService.findAll();
    }
}