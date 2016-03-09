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
 
 * @author Iman H
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    
    @Context
    ServletContext context;
    @Override
    public Response toResponse(NotFoundException e) {
        JsonObject jo = new JsonObject();
        
        if (Boolean.valueOf(context.getInitParameter("debug"))) {
            
            String err = "";
            
            StackTraceElement[] stack = e.getStackTrace();
            
            for (StackTraceElement elm : stack) {
                err += elm.toString() + "\n";
            }
            
            jo.addProperty("stackTrace", err);
        }
        
        jo.addProperty("code", 404);
        jo.addProperty("message", "Person not found.");
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}
