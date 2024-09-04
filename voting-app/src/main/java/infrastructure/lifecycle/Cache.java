package infrastructure.lifecycle;

import infrastructure.repositories.RedisElectionRepository;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;


@Startup
@ApplicationScoped
public class Cache {
    private static final Logger LOGGER = Logger.getLogger(Cache.class);

    public Cache(RedisElectionRepository repository) {
        LOGGER.info("Startup: Cache");
        repository.findAll();
    }
}
