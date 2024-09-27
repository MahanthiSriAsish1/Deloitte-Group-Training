package com.deloitte.demo.resource;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.service.DepartmentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
public class DepartmentResource {
    private DepartmentService departmentService = new DepartmentService();

    // Get all departments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return Response.ok(departments)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }

    // Add a new department
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDepartment(Department department) {
        Department addedDept = departmentService.addDepartment(department);
        return Response.status(Response.Status.CREATED)
                .entity(addedDept)
                .header("Access-Control-Allow-Origin", "*")
                .header("message", "Department added successfully!")
                .build();
    }

    // Get department by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") int id) {
        Department department = departmentService.getDepartmentById(id);
        return department != null
                ? Response.ok(department)
                    .header("Access-Control-Allow-Origin", "*")
                    .build()
                : Response.status(Response.Status.NOT_FOUND)
                    .entity("Department not found")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
    }

    // Update department
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@PathParam("id") int id, Department updatedDepartment) {
        Department dept = departmentService.updateDepartment(id, updatedDepartment);
        return dept != null
                ? Response.ok(dept)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("message", "Department updated successfully!")
                    .build()
                : Response.status(Response.Status.NOT_FOUND)
                    .entity("Department not found")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
    }

    // Delete department
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@PathParam("id") int id) {
        boolean isDeleted = departmentService.deleteDepartment(id);
        return isDeleted
                ? Response.ok("Department deleted successfully!")
                    .header("Access-Control-Allow-Origin", "*")
                    .build()
                : Response.status(Response.Status.NOT_FOUND)
                    .entity("Department not found")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
    }

    // CORS options
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
