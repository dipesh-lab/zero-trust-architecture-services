package io.s2s.reports;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ReportServiceMain {

	public static void main(String[] args) {
		Quarkus.run(args);
	}
}

// https://quarkus.io/guides/security-openid-connect-client
// https://quarkus.io/guides/security-openid-connect-client-reference