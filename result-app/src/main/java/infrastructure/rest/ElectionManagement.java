package infrastructure.rest;

import api.dto.Election;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import java.util.List;

@RegisterRestClient(configKey = "election-management")
public interface ElectionManagement {
    @GET
    @Path("/api/elections")
    Uni<List<Election>> getElections();
}
