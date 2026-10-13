package com.satyam.employee_management_system.mapper;

import com.satyam.employee_management_system.dto.EmployeeDto;
import com.satyam.employee_management_system.model.Employee;

public class EmployeeMapper {

    // Convert Employee Entity into EmployeeDto
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }

    // Convert EmployeeDto into Employee Entity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartment(),
                employeeDto.getSalary()
        );
    }
}
