package org.example.tasks2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double money;
}
