package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import api.dto.out.Candidate;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.instancio.Instancio;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestHTTPEndpoint(CandidateResource.class)
class CandidateResourceTest {
    @InjectMock
    CandidateApi api;

    @Test
    void create() {
        var in = Instancio.create(CreateCandidate.class);

        given().contentType(MediaType.APPLICATION_JSON).body(in)
               .when().post()
               .then().statusCode(RestResponse.StatusCode.CREATED);

        verify(api).create(in);
        verifyNoMoreInteractions(api);
    }

    @Test
    void list() {
        var out = Instancio.stream(Candidate.class).limit(4).toList();

        when(api.list()).thenReturn(out);

        var response = given()
                .when().get()
                .then().statusCode(RestResponse.StatusCode.OK).extract().as(Candidate[].class);

        verify(api).list();
        verifyNoMoreInteractions(api);

        assertEquals(out, Arrays.stream(response).toList());
    }

    @Test
    void update(){
        var in = Instancio.create(UpdateCandidate.class);
        var out = Instancio.create(Candidate.class);

        when(api.update(out.id(), in)).thenReturn(out);

        given().contentType(MediaType.APPLICATION_JSON).body(in)
                .when().put("/" + out.id())
                .then().statusCode(RestResponse.StatusCode.OK);

        verify(api).update(out.id(), in);
        verifyNoMoreInteractions(api);
    }
}