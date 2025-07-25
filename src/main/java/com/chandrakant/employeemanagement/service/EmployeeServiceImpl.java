package com.chandrakant.employeemanagement.service;

import com.chandrakant.employeemanagement.dao.EmployeeRepository;
import com.chandrakant.employeemanagement.dto.EmployeeDto;
import com.chandrakant.employeemanagement.entity.Employee;
import com.chandrakant.employeemanagement.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService{


    RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private  EmployeeRepository employeeRepository;




    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.info("Inside getAllEmployees() method");
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            log.info("No employees found");
        }
        restTemplate.delete("http://localhost:8080/employees");
        log.info("Employees found: {}", employees);

        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException {
        log.info("Inside getEmployeeById() method with id: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new ResourceNotFoundException("Employee not found for ID: " + id);
        }
        Employee employee = employeeOptional.get();
        EmployeeDto employeeDTO = modelMapper.map(employee, EmployeeDto.class);
        log.info("Employee found: {}", employeeDTO);
        return employeeDTO;
    }

    @Override
    public EmployeeDto saveEmployee(Employee employee) {
        log.info("Inside saveEmployee() method");
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        log.info("Employee saved: {}", employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(Employee employee,Long employeeId) {
        log.info("Inside updateEmployee() method");
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setDesignation(employee.getDesignation());
            existingEmployee.setSalary(employee.getSalary());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            EmployeeDto employeeDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
            log.info("Employee updated: {}", employeeDto);
            return employeeDto;
        }
        return null;
    }

    @Override
    public String deleteEmployee(Long id) {
        log.info("Inside deleteEmployee() method with id: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.deleteById(id);
            log.info("Employee deleted with id: {}", id);
            return "Employee deleted successfully";
        }
        return "Employee deleted successfully";
    }
}
