package io.s2s.location.rest;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

@Path("/countries")
@Authenticated
public class CountryResource {

    private static final Logger LOG = LoggerFactory.getLogger(CountryResource.class);

    private static final Map<String, String> COUNTRIES = Map.of("AU", "Australia", "BE", "Belgium",
            "IN", "India", "UK", "United Kingdom", "US", "United States");

    @GET
    @Path("/")
    @RolesAllowed({"Location.Basic", "Location.Full"})
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getCountries() {
        LOG.info("Get country names");
        return COUNTRIES;
    }

    @GET
    @Path("/search")
    @RolesAllowed({"Location.Full"})
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String>  search(@QueryParam("query") String query) {
        LOG.info("Get countries 2 letter iso codes");
        return COUNTRIES.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(query))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
}
