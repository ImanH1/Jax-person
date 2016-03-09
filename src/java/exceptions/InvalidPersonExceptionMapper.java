/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Iman H
 */
@Provider
public class InvalidPersonExceptionMapper implements ExceptionMapper<InvalidPersonException> {
    @Context
    ServletContext context;
    @Override
    public Response toResponse(InvalidPersonException e) {
        JsonObject jo = new JsonObject();
        
        if (Boolean.valueOf(context.getInitParameter("debug"))) {
            
            String err = "";
            
            StackTraceElement[] stack = e.getStackTrace();
            
            for (StackTraceElement elm : stack) {
                err += elm.toString() + "\n";
            }
            
            jo.addProperty("stackTrace", err);
        }
        
        jo.addProperty("code", 400);
        jo.addProperty("message", "First Name or Last Name is missing");
        return Response.status(Response.Status.BAD_REQUEST).entity(jo.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
