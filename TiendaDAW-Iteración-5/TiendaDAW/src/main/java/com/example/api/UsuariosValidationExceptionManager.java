package com.example.api;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsuariosValidationExceptionManager implements ExceptionMapper<ConstraintViolationException> {
    public record ErrorValidacion (String name, String message) {} //temp DTO record class
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<Object> errors = new ArrayList<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
//attribute name is the last part, e.g. method.arg0.propname
            String[] parts=cv.getPropertyPath().toString().split("\\.");
            errors.add ( new ErrorValidacion( parts[parts.length-1] , cv.getMessage() ));
        };
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}
