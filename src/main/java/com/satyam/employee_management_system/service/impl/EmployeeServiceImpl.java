package com.satyam.employee_management_system.service.impl;

import com.satyam.employee_management_system.dto.EmployeeDto;
import com.satyam.employee_management_system.exception.ResourceNotFoundException;
import com.satyam.employee_management_system.mapper.EmployeeMapper;
import com.satyam.employee_management_system.model.Employee;
import com.satyam.employee_management_system.repository.EmployeeRepository;
import com.satyam.employee_management_system.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor Injection (Best Practice for Dependency Injection)
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        // Dto to Entity Conversion
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        // Entity to Dto Conversion
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                        .map(EmployeeMapper::mapToEmployeeDto)
                        .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDepartment(employeeDto.getDepartment());
        existingEmployee.setSalary(employeeDto.getSalary());
        
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesWithPaginationAndSorting(int pageNo, int pageSize, String sortBy) {

        String cleanSortBy = sortBy.split(":")[0].trim();
        Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by(cleanSortBy));
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<Employee> listOfEmployees = employeePage.getContent();
        return listOfEmployees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }
}

