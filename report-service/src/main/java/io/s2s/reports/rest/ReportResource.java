package io.s2s.reports.rest;

import io.quarkus.security.Authenticated;
import io.s2s.reports.clients.LocationServiceClient;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/reports")
@Authenticated
public class ReportResource {

    private static final Logger LOG = LoggerFactory.getLogger(ReportResource.class);

    private final LocationServiceClient locationServiceClient;

    @Inject
    public ReportResource(@RestClient LocationServiceClient locationServiceClient) {
        this.locationServiceClient = locationServiceClient;
    }

    @GET
    @Path("/users")
    @RolesAllowed({"Report.Basic", "Report.Full"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getUsers() {
        LOG.info("Get New User report");
        LOG.info("Get countries");
        var countries = locationServiceClient.getCountries();
        LOG.info("Countries {}", countries);
        return List.of("AU User", "BE User");
    }

    @GET
    @Path("/admins")
    @RolesAllowed({"Report.Full"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAdmins() {
        LOG.info("Get New Admin report");
        LOG.info("Search countries");
        var countries = locationServiceClient.search("aus");
        LOG.info("Countries {}", countries);
        return List.of("AU Admin", "BE Admin");
    }
}
