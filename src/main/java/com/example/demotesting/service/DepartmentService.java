package com.example.demotesting.service;


import com.example.demotesting.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DepartmentService {
     Employee getEmployeeWithMaxSalary(Integer departmentId);
    Employee getEmployeeWithMinSalary(Integer departmentId);
    Map<Integer, List<Employee>> getGroupedByDepartmentEmployees(Integer departmentId);

    int getSumSalaryByDepartment(Integer departmentId);
}
