package io.s2s.location;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class LocationServiceMain {

	public static void main(String[] args) {
		Quarkus.run(args);
	}
}
