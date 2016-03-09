/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.JsonObject;
import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import control.PersCtrl;
import entity.Person;
import exceptions.InvalidIdException;
import exceptions.InvalidPersonException;
import exceptions.NotFoundException;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import util.Converter;

/**
 *
 * @author Iman H
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpBeforeClass() {
        baseURI = "http://localhost:8084/REST-With-JAX-RS-Person/";
        defaultParser = Parser.JSON;
        basePath = "api/persons";
    }
    
    @Test
    public void testBadLogin(){
        given()
                .authentication().basic("badusername","isuckatloggingin")
                .when()
                .delete("/1")
                .then()
                .statusCode(401);
    }

    @Test
    public void testGetPersonNotFound() {
        when()
            .get("/2223")
        .then()
            .statusCode(404)
            .body("message", equalTo("Person not found."))
            .body("code", equalTo(404));
    }
    
    @Test
    public void testCreatePerson() throws NotFoundException, InvalidIdException, InvalidPersonException {
        
        Person p = new Person();
        p.setFName("Baby");
        p.setLName("Dave");
        p.setPhone("420420420");
        p.setId(420);
        given()
                .contentType(ContentType.JSON)
                .authentication().basic("person","test")
                .body(p)
                .when()
                .post("/")
                .then()
                .statusCode(200);

        given()
                .authentication().basic("person","test")
        .when()
                .delete("/420")
                .then()
                .statusCode(200);
    }
    
    @Test
    public void testInvalidPersonByException() throws InvalidPersonException {
        
        
        Person person = new Person();
        person.setPhone("420420420420");
        String p = Converter.getJSONFromPerson(person);
        
        
        given()
            .contentType("application/json")
                .authentication().basic("person","test")
            .body(p)
        .when()
            .post("")
        .then()
            .statusCode(400)
            .body("message", equalTo("First Name or Last Name is missing"))
            .body("code", equalTo(400));
    }
    
    @Test
    public void testGetPersonID() {
        
        when()
            .get("/3")
        .then()
            .statusCode(200)
            .body("fName", equalTo("Destiny"))
            .body("lName", equalTo("Golden"))
            .body("phone", equalTo("083-664-5554"))
            .body("id", equalTo(3));
    }
    
    @Test
    public void testDeleteNonExistingPerson() throws NotFoundException {
        
        given()
                .authentication().basic("person","test")
        .when()
            .delete("/42069")
        .then()
            .statusCode(200)
            .body("message", equalTo("Person not found."))
            .body("code", equalTo(404));
    }
    
}
