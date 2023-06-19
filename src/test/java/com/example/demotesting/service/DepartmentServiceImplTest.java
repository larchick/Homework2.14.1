package com.example.demotesting.service;

import com.example.demotesting.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Assertions.assertEquals(employees.get(0), employeeWithMaxSalary);
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
        Employee employeeWithMinSalary = departmentService.getEmployeeWithMinSalary(departmentId);

        //then
        Assertions.assertEquals(employees.get(2), employeeWithMinSalary);
    }

    @Test
    public void shouldReturnSumSalaryByDepartment() {
        //given

        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }

        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        int sumSalary = departmentService.getSumSalaryByDepartment(departmentId);

        //then
        Assertions.assertEquals(350_000, sumSalary);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    void shouldReturnNullWhenNoEmployeesInDepartment(){
        //given
        final int departmentId = 2;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        //when
        Employee employeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(departmentId);

        //then
        Assertions.assertNull(employeeWithMaxSalary);


    }

    @Test
    public void shouldReturnGroupedEmployeesByNonExistDepartment() {
        //given

        final int departmentId = 1;


        final Map<String, Employee> employeeMap = new HashMap<>();
        Map<Integer, List<Employee>> result = new HashMap<>();
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        Map employeesGroupedByDepartment = departmentService.getGroupedByDepartmentEmployees(departmentId);

        //then
        Assertions.assertEquals(result, employeesGroupedByDepartment);
        verify(employeeService, times(1)).getAllEmployees();

    }
}