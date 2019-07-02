/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import java.util.List;
import metrodatamii.metrodatamii.entities.Employee;
import metrodatamii.metrodatamii.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arif Fridasari
 */
@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }
}
