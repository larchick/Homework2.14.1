package com.example.demotesting.service;


import com.example.demotesting.exception.EmployeeAlreadyAddedException;
import com.example.demotesting.exception.EmployeeNotFoundException;
import com.example.demotesting.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int EMPLOYEE_STORAGE_SITE = 5;
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) {
        String employeeKey = getEmployeeKey(firstName, lastName);

        if (employees.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        if (employees.size() == EMPLOYEE_STORAGE_SITE) {
            throw new EmployeeAlreadyAddedException("Хранилище переполнено");
        }

        employees.put(
                employeeKey,
                new Employee(firstName, lastName, salary, department)
        );

        return employees.get(employeeKey);
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String employeeKey = getEmployeeKey(firstName, lastName);

        if (!employees.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException("Такого сотрудника нет");
        }

        return employees.get(employeeKey);

    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String employeeKey = getEmployeeKey(firstName, lastName);

        if (!employees.containsKey(employeeKey)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }
        return employees.get(employeeKey);
    }

    @Override
    public Collection<Employee> findAll() {
        return null;
    }

    @Override
    public Map<String, Employee> getAllEmployees(){ return employees;}

    private String getEmployeeKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
