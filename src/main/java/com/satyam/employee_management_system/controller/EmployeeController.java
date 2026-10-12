package com.satyam.employee_management_system.controller;

import com.satyam.employee_management_system.model.Employee;
import com.satyam.employee_management_system.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Build Create Employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // 2. Build Get All Employees REST API
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // 3. Build Get Employee By ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    // 4. Build Update Employee REST API
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId, 
                                                   @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
    }

    // 5. Build Delete Employee REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }
}

