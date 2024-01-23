package io.s2s.location.filter;

import io.s2s.location.utils.RequestUtils;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Provider
public class RequestIdFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(RequestIdFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        final List<String> requestIds = requestContext.getHeaders().get(RequestUtils.TRACE_ID);
        final String requestId = Optional.ofNullable(requestIds)
                .filter(Predicate.not(List::isEmpty))
                .map(ids -> ids.get(0))
                .orElse(RequestUtils.generateRequestId());
        MDC.put(RequestUtils.LOG_TRACE_ID, requestId);
        LOG.info("Request received. Endpoint {} - {}", requestContext.getMethod(), requestContext.getUriInfo().getPath());
    }
}
