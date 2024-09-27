package com.deloitte.demo.resource;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {
    private EmployeeService employeeService = new EmployeeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return Response.ok(employees)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee) {
        Employee emp = employeeService.addEmployee(employee);
        return Response.status(Response.Status.CREATED)
                .entity(emp)
                .header("Access-Control-Allow-Origin", "*")
                .header("message", "Employee added successfully!")
                .build();
    }

    @SuppressWarnings("resource")
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") int id) {
        return employeeService.getEmployeeById(id)
                .map(emp -> Response.ok(emp)
                        .header("Access-Control-Allow-Origin", "*")
                        .build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee not found")
                        .header("Access-Control-Allow-Origin", "*")
                        .build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") int id, Employee updatedEmployee) {
        Employee emp = employeeService.updateEmployee(id, updatedEmployee);
        return emp != null
                ? Response.ok(emp)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("message", "Employee updated successfully!")
                    .build()
                : Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee not found")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        return isDeleted
                ? Response.ok("Employee deleted successfully!")
                    .header("Access-Control-Allow-Origin", "*")
                    .build()
                : Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee not found")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
    }

    @OPTIONS
    @Path("/")
    public Response cors() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*") // Allow any origin
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }
}
