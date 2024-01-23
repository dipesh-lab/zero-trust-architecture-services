package io.s2s.reports.clients;

import io.quarkus.oidc.token.propagation.AccessTokenRequestFilter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@RegisterRestClient
@RegisterProvider(AccessTokenRequestFilter.class)
//@RegisterClientHeaders(RestClientHeadersFactory.class)
public interface LocationServiceClient {

	@GET
	@Path("/countries")
	@Produces(MediaType.APPLICATION_JSON)
	Map<String, String> getCountries();


	@GET
	@Path("/countries/search")
	@Produces(MediaType.APPLICATION_JSON)
	Map<String, String> search(@QueryParam("query") String query);
}
