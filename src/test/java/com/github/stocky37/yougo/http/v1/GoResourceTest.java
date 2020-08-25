package com.github.stocky37.yougo.http.v1;

import static org.hamcrest.CoreMatchers.is;

import com.github.stocky37.yougo.TestUtils;
import com.github.stocky37.yougo.db.GoRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GoResourceTest {
	@Inject
	GoRepository repository;

	//	@BeforeAll
	//	static void init() {
	//		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
	//	}

	@BeforeEach
	@Transactional
	void before() {
		RestAssured.basePath = "/go/{alias}";
		repository.deleteAll();
	}

	@Test
	void go() {
		final JsonObject generated = TestUtils.insertGo(RestAssured.given());
		RestAssured
			.given()
			.pathParam("alias", generated.getString("alias"))
			.get()
			.then()
			.statusCode(200)
			.body("id", is(generated.getString("id")))
			.body("alias", is(generated.getString("alias")))
			.body("href", is(generated.getString("href")));
	}

	@Test
	void goNotFound() {
		RestAssured.given().pathParam("alias", "notfound").get().then().statusCode(404);
	}
}
