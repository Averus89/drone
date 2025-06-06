package pl.dexbytes.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AiInstructionsResponse {
    private String _thinking;
    private String description;
}
