package com.example.demotesting.controller;


import com.example.demotesting.model.Employee;
import com.example.demotesting.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMinSalary(id);
    }

    @GetMapping("/{id}/employees")
    public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees(@PathVariable Integer id) {
        return departmentService.getGroupedByDepartmentEmployees(id);
    }


}
