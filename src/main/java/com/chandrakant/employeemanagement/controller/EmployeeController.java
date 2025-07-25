package com.chandrakant.employeemanagement.controller;

import com.chandrakant.employeemanagement.dto.EmployeeDto;
import com.chandrakant.employeemanagement.entity.Employee;
import com.chandrakant.employeemanagement.exception.ResourceNotFoundException;
import com.chandrakant.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() throws ResourceNotFoundException {
        log.info("Inside getAllEmployees() method");
        try{
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        if(employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }
        log.info("Employees found: {}", employees);
        return ResponseEntity.ok(employees);
        }
        catch (ResourceNotFoundException e) {
            throw e;
        }
    }
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("Inside getEmployeeById() method with id: {}", id);
        try{
        EmployeeDto employee = employeeService.getEmployeeById(id);
        if (employee == null || employee.getEmployeeId() == null) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        log.info("Employee found: {}", employee);
        return ResponseEntity.ok(employee);
        }
        catch (ResourceNotFoundException e) {
            throw e;
        }
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid Employee employee) {
        log.info("Inside saveEmployee() method with employee: {}", employee);
        EmployeeDto savedEmployee = employeeService.saveEmployee(employee);
        log.info("Employee saved: {}", savedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody @Valid Employee employee, @PathVariable Long employeeId) throws ResourceNotFoundException {
        log.info("Inside updateEmployee() method with employee: {}", employee);
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employee, employeeId);
        if (updatedEmployee == null) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        log.info("Employee updated: {}", updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) throws ResourceNotFoundException {
        log.info("Inside deleteEmployee() method with employeeId: {}", employeeId);
        if(employeeService.getEmployeeById(employeeId) == null) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        employeeService.deleteEmployee(employeeId);
        log.info("Employee deleted with id: {}", employeeId);
        return ResponseEntity.ok("Employee deleted with id: " + employeeId);
    }
}
