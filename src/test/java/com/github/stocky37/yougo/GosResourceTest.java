package com.github.stocky37.yougo;

import static com.github.stocky37.yougo.TestUtils.insertGo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.github.stocky37.yougo.db.GoRepository;
import com.github.stocky37.yougo.util.MoreMediaTypes;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.security.Principal;
import java.util.UUID;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class GosResourceTest {
	@Inject
	GoRepository repository;

	@InjectMock
	SecurityIdentity securityIdentity;

	@InjectMock
	Principal principal;

	//		@BeforeAll
	//		static void init() {
	//			RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
	//		}

	@BeforeEach
	@Transactional
	void before() {
		Mockito.when(securityIdentity.isAnonymous()).thenReturn(false);
		Mockito.when(securityIdentity.getPrincipal()).thenReturn(principal);
		Mockito.when(principal.getName()).thenReturn("testIdentity");

		RestAssured.basePath = "/gos";
		repository.deleteAll();
		RestAssured.config =
			RestAssuredConfig
				.config()
				.encoderConfig(
					EncoderConfig
						.encoderConfig()
						.encodeContentTypeAs(MoreMediaTypes.JSON_MERGE_PATCH, ContentType.JSON)
				);
	}

	@Test
	void create() {
		final JsonObject generated = TestUtils.generateGo();
		ValidatableResponse response = RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(201)
			.body("id", notNullValue())
			.body("alias", is(generated.getString("alias")))
			.body("href", is(generated.getString("href")));

		response.header(
			"location",
			is(
				String.format(
					"%s:%s/gos/%s",
					RestAssured.baseURI,
					RestAssured.port,
					generated.getString("alias")
				)
			)
		);

		validateGosCount(1);
	}

	@Test
	void create400InvalidHref() {
		final JsonObject generated = Json
			.createObjectBuilder(TestUtils.generateGo())
			.add("href", "badhref")
			.build();

		RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(400);

		validateGosCount(0);
	}

	@Test
	void create400MissingHref() {
		final JsonObject generated = Json
			.createObjectBuilder(TestUtils.generateGo())
			.remove("href")
			.build();

		RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(400);

		validateGosCount(0);
	}

	@Test
	void create400EmptyHref() {
		final JsonObject generated = Json
			.createObjectBuilder(TestUtils.generateGo())
			.add("href", "")
			.build();

		RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(400);

		validateGosCount(0);
	}

	@Test
	void create400InvalidAlias() {
		final JsonObject generated = Json
			.createObjectBuilder(TestUtils.generateGo())
			.add("alias", "bad alias")
			.build();

		RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(400);

		validateGosCount(0);
	}

	@Test
	void create400MissingAlias() {
		final JsonObject generated = Json
			.createObjectBuilder(TestUtils.generateGo())
			.remove("alias")
			.build();

		RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(400);

		validateGosCount(0);
	}

	@Test
	void create400EmptyAlias() {
		final JsonObject generated = Json
			.createObjectBuilder(TestUtils.generateGo())
			.add("alias", "")
			.build();

		RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(generated.toString())
			.post("/")
			.then()
			.statusCode(400);

		validateGosCount(0);
	}

	@Test
	public void findAll() {
		insertGo(RestAssured.given(), "a");
		Mockito.when(principal.getName()).thenReturn("altIdentity");
		final JsonObject b = insertGo(RestAssured.given(), "b");
		final JsonObject c = insertGo(RestAssured.given(), "c");

		validateGosCount(2).body("[0].id", is(b.getString("id"))).body("[1].id", is(c.getString("id")));
	}

	@Test
	public void find() {
		final JsonObject generated = insertGo(RestAssured.given());
		RestAssured
			.given()
			.when()
			.pathParam("alias", generated.getString("alias"))
			.get("/{alias}")
			.then()
			.statusCode(200)
			.body("id", is(generated.getString("id")))
			.body("alias", is(generated.getString("alias")))
			.body("href", is(generated.getString("href")));
	}

	@Test
	public void find404NotFound() {
		RestAssured
			.given()
			.pathParam("id", UUID.randomUUID())
			.when()
			.get("/{id}")
			.then()
			.statusCode(404);
	}

	@Test
	public void updateAlias() {
		final JsonObject generated = insertGo(RestAssured.given());
		final JsonObject update = TestUtils.generateGo();

		RestAssured
			.given()
			.contentType(MoreMediaTypes.JSON_MERGE_PATCH)
			.pathParam("alias", generated.getString("alias"))
			.body(update.toString())
			.patch("/{alias}")
			.then()
			.statusCode(200)
			.body("id", is(generated.getString("id")))
			.body("alias", is(update.getString("alias")))
			.body("href", is(update.getString("href")));

		RestAssured
			.given()
			.pathParam("alias", update.getString("alias"))
			.get("/{alias}")
			.then()
			.statusCode(200)
			.body("id", is(generated.getString("id")))
			.body("alias", is(update.getString("alias")))
			.body("href", is(update.getString("href")));
	}

	@Test
	public void delete() {
		final JsonObject generated = insertGo(RestAssured.given());

		RestAssured
			.given()
			.pathParam("alias", generated.getString("alias"))
			.delete("/{alias}")
			.then()
			.statusCode(200)
			.body("id", is(generated.getString("id")))
			.body("alias", is(generated.getString("alias")))
			.body("href", is(generated.getString("href")));

		validateGosCount(0);
	}

	@Test
	public void delete404NotFound() {
		RestAssured.given().pathParam("alias", "nonexistent").delete("/{alias}").then().statusCode(404);
	}

	private static ValidatableResponse validateGosCount(final int count) {
		return RestAssured.given().when().get().then().statusCode(200).body("size()", is(count));
	}
}
