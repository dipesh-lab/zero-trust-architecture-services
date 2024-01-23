package io.s2s.location.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Provider
public class RequestFilter implements ClientRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        LOG.info("Client request filtered...");
    }
}
