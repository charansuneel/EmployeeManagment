package com.employee.managment.demo.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MetroStations")
public class MetroEntity {
    private String value;
    private String name;
}
