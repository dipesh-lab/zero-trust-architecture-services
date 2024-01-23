package io.s2s.reports.utils;

import java.util.UUID;

public final class RequestUtils {

    public static final String TRACE_ID = "x-trace-id";
    public static final String LOG_TRACE_ID = "traceId";

    public static String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    private RequestUtils() {}
}
