/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.JsonObject;
import entity.Person;
import exceptions.InvalidIdException;
import exceptions.InvalidPersonException;
import exceptions.NotFoundException;
import control.PersCtrl;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.Converter;

@Path("persons")
public class PersonsResource {

    @Context
    private UriInfo context;
    PersCtrl ctrl = new PersCtrl("PersonsPU");

    /**
     * Creates a new instance of PersonsResource
     */
    public PersonsResource() {
    }

    /**
     * Returns a person.
     *
     * @param id of the quote to return
     * @return
     */
    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int id) throws NotFoundException {

        String person = Converter.getJSONFromPerson(ctrl.getPerson(id));

        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(person.toString())
                .build();
    }

    /**
     * Returns all persons.
     *
     * @return json object with persons
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() throws NotFoundException {

        String persons = Converter.getJSONFromPersonList(ctrl.getPersons());

        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(persons.toString())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(String json) throws InvalidIdException, InvalidPersonException {

        Person p = ctrl.addPerson(Converter.getPersonFromJson(json));
        String res = Converter.getJSONFromPerson(p);

        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(res)
                .build();
    }

    /**
     *
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id) throws NotFoundException {

        Person p = ctrl.deletePerson(id);
        String res = Converter.getJSONFromPerson(p);

        return Response
                .status(Response.Status.OK)
                .entity(res.toString())
                .build();
    }
}
