package com.example.demotesting.service;

import com.example.demotesting.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final List<Employee> employees = new ArrayList<>() {{
        add(new Employee("Ivan", "Ivanov", 50_000, 1));
        add(new Employee("Petya", "Petrov", 100_000, 1));
        add(new Employee("Alexandr", "Dog", 200_000, 1));
    }};

    @Test
    void shouldReturnEmployeeWithMaxSalary() {
        //given

        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        //when
        Employee employeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(departmentId);

        //then
        Assertions.assertEquals(employees.get(2), employeeWithMaxSalary);


    }

    @Test
    void shouldReturnEmployeeWithMinSalary() {

        //given

        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        //when
        Employee employeeWithMinSalary = departmentService.getEmployeeWithMaxSalary(departmentId);

        //then
        Assertions.assertEquals(employees.get(2), employeeWithMinSalary);
    }
}