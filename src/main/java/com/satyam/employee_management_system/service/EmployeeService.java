package com.satyam.employee_management_system.service;

import com.satyam.employee_management_system.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
    void deleteEmployee(Long id);
}

