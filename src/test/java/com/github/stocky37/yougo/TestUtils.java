package com.github.stocky37.yougo;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;

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
		return Json
			.createObjectBuilder()
			.add("alias", prefix + faker.lorem().word())
			.add("href", "http://" + faker.company().url())
			.build();
	}

	public static JsonObject insertGo(final RequestSpecification req) {
		return insertGo(req, "");
	}

	// generate a go with an alias with the given prefix
	// this is used in order to ensure consistency in the findAll() test
	// as that response is ordered by alias
	public static JsonObject insertGo(final RequestSpecification req, final String prefix) {
		final JsonObject generated = TestUtils.generateGo(prefix);
		final String id = extractId(
			RestAssured
				.given()
				.basePath("/gos")
				.contentType(ContentType.JSON)
				.body(generated.toString())
				.post()
				.then()
		);
		return Json.createObjectBuilder(generated).add("id", id).build();
	}

	public static String extractId(ValidatableResponse response) {
		return response.extract().body().path("id");
	}
}
