package domain;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        when(repositoryMock.findAll()).thenReturn(candidates);

        List<Candidate> results = candidateService.findAll();

        verify(repositoryMock).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(candidates, results);
    }

    @Test
    void findById_whenCandidateIsFound_returnCandidate(){
        Candidate candidate = Instancio.create(Candidate.class);

        when(repositoryMock.findById(candidate.id())).thenReturn(Optional.of(candidate));

        Candidate result = candidateService.findById(candidate.id());

        verify(repositoryMock).findById(candidate.id());
        verifyNoMoreInteractions(repositoryMock);

        assertEquals(result, candidate);
    }

    @Test
    void findById_whenCandidateIsNotFound_throwsException(){
        Candidate candidate = Instancio.create(Candidate.class);

        when(repositoryMock.findById(candidate.id())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> candidateService.findById(candidate.id()));

        verify(repositoryMock).findById(candidate.id());
        verifyNoMoreInteractions(repositoryMock);
    }
}