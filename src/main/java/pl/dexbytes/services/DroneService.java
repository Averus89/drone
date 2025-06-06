package pl.dexbytes.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import pl.dexbytes.ai.DroneInterpreter;
import pl.dexbytes.model.AiInstructionsResponse;
import pl.dexbytes.model.DroneResponse;

@ApplicationScoped
@Slf4j
public class DroneService {
    @Inject
    DroneInterpreter droneInterpreter;

    public DroneResponse interpret(String instruction) {
        log.info("Interpreting: {}", instruction);
        AiInstructionsResponse response = droneInterpreter.interpret(instruction);
        log.info("Response: {}", response);
        return DroneResponse.builder().description(response.getDescription()).build();
    }

}
