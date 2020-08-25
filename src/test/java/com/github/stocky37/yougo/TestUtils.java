package com.github.stocky37.yougo;

import com.github.javafaker.Faker;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class TestUtils {
	private static final Faker faker = new Faker();

	public static String resource(final String name) throws IOException {
		return new String(Objects.requireNonNull(resourceStream(name)).readAllBytes());
	}

	public static JsonObject jsonResource(final String name) {
		return Json.createReader(resourceStream(name)).readObject();
	}

	private static InputStream resourceStream(final String name) {
		return TestUtils.class.getClassLoader().getResourceAsStream(name);
	}

	public static JsonObject generateGo() {
		return generateGo("");
	}

	public static JsonObject generateGo(final String prefix) {
		return Json.createObjectBuilder()
			.add("alias", prefix + faker.lorem().word())
			.add("href", "http://" + faker.company().url())
			.build();
	}
}
