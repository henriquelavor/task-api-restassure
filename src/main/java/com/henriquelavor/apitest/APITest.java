package com.henriquelavor.apitest;


import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
	}

	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(CoreMatchers.is(200))
		;
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given()
		.body("{\"task\":\"Entrevista TQ sabado as 14h\",\"dueDate\":\"2021-10-01\"}")
		.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(CoreMatchers.is(201))
		;
	}
	
	@Test
	public void nadoDeveAdicionarTarefaInvalida() {
		RestAssured.given()
		.body("{\"task\":\"Entrevista TQ sabado as 14h\",\"dueDate\":\"2010-10-01\"}")
		.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(CoreMatchers.is(400))
			.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}
	
	
}
