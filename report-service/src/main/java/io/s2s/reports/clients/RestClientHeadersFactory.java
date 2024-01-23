package io.s2s.reports.clients;

import io.s2s.reports.utils.RequestUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Optional;

@ApplicationScoped
public class RestClientHeadersFactory implements ClientHeadersFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RestClientHeadersFactory.class);


    public RestClientHeadersFactory() {
    }

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> inHeaders,
                                                 MultivaluedMap<String, String> outHeaders) {
        final String requestId = Optional.ofNullable(MDC.get(RequestUtils.LOG_TRACE_ID))
                .orElse(RequestUtils.generateRequestId());
        outHeaders.add(RequestUtils.TRACE_ID, requestId);
        return outHeaders;
    }
}
