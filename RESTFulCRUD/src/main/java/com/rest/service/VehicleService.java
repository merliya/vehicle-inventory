package com.rest.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.dao.VehicleDAO;
import com.rest.model.Vehicle;

@Path("/vehicles")
public class VehicleService {

	private VehicleDAO vDao = new VehicleDAO();
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Vehicle> getVehicles_JSON() {
        List<Vehicle> listOfVehicles = vDao.getAllVehicles();
        return listOfVehicles;
    }
	
	
	@POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Vehicle addVehicle(Vehicle v) {		
        return vDao.addVehicle(v);
    }
	
	 @PUT
	 @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	 public Vehicle updateVehicle(Vehicle v) {
	      return vDao.UpdateVehicle(v);
	 }
	 
	 @DELETE
	 @Path("/{vehicleId}")
	 @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	 public void deleteVehicle(@PathParam("vehicleId") String vehicleId) {
		 vDao.deleteVehicle(vehicleId);
	 }
}
