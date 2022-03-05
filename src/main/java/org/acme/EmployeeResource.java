package org.acme;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.Path;
import javax.ws.rs.POST;


@Path("/employees")
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @POST
    public void create(Employee employee) throws JsonProcessingException {
        employeeService.insert(employee);
    }    

    @GET
    @Path("/{key}")
    public Employee get(@PathParam("key") String key) throws JsonMappingException, JsonProcessingException {
        return employeeService.get(key);
    }

    @DELETE
    public void delete() throws JsonProcessingException {
        employeeService.delete();
    }    

}