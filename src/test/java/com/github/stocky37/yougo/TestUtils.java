package com.github.stocky37.yougo;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TestUtils {
	private static final Faker faker = new Faker();

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
				.statusCode(201)
		);
		return Json.createObjectBuilder(generated).add("id", id).build();
	}

	public static String extractId(ValidatableResponse response) {
		return response.extract().body().path("id");
	}
}
