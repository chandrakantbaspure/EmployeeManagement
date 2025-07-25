package com.chandrakant.employeemanagement.service;

import com.chandrakant.employeemanagement.dto.EmployeeDto;
import com.chandrakant.employeemanagement.entity.Employee;
import com.chandrakant.employeemanagement.exception.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDto> getAllEmployees();
    public EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException;
    public EmployeeDto saveEmployee(Employee employee);
    public EmployeeDto updateEmployee(Employee employee,Long EmployeeId);
    public String deleteEmployee(Long employeeId);
}
