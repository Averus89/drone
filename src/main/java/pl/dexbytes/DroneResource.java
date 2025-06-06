package pl.dexbytes;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pl.dexbytes.ai.DroneInterpreter;
import pl.dexbytes.model.DroneRequest;
import pl.dexbytes.model.DroneResponse;
import pl.dexbytes.services.DroneService;

@Path( "/drone")
public class DroneResource {
    @Inject
    DroneService droneService;

    @POST
    @Path("/execute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DroneResponse drone(DroneRequest request) {
        return droneService.interpret(request.getInstruction());
    }
}
