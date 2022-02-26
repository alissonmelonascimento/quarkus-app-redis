package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import java.util.List;

import io.smallrye.mutiny.Uni;

@Path("/increments")
public class EmployeeResource {

    @Inject
    IncrementService service;

    @Inject
    EmployeeService employeeService;

    @GET
    public Uni<List<String>> keys() {
        return service.keys();
    }

    /*@POST
    public Increment create(Increment increment) {
        service.set(increment.key, increment.value);
        return increment;
    }*/

    @POST
    public void create(Employee employee) throws JsonProcessingException {
        employeeService.insert(employee);
    }    

    /*@GET
    @Path("/{key}")
    public Increment get(@PathParam("key") String key) {
        return new Increment(key, Integer.valueOf(service.get(key)));
    }*/

    @GET
    @Path("/{key}")
    public Employee get(@PathParam("key") String key) throws JsonMappingException, JsonProcessingException {
        return employeeService.get(key);
    }

    @PUT
    @Path("/{key}")
    public void increment(@PathParam("key") String key, Integer value) {
        service.increment(key, value);
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(@PathParam("key") String key) {
        return service.del(key);
    }

}