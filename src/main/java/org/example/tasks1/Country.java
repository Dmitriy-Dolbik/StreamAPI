package org.example.tasks1;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {
    String name;
    Long population;
}
