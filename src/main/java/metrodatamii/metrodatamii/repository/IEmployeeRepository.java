/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.repository;

import java.util.List;
import metrodatamii.metrodatamii.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arif Fridasari
 */
public interface IEmployeeRepository extends CrudRepository<Employee, String> {

    @Query(value = "SELECT * FROM employee WHERE is_delete = 'false'", nativeQuery = true)
    List<Employee> getAll();

    @Query(value = "SELECT * FROM employee WHERE id = ?1", nativeQuery = true)
    List<Employee> getEmployeeById(String id);

//    @Query(value = "UPDATE Employee e SET e.id=?1, e.first_name = ?2, e.last_name = ?2, e.email = ?3, e.salary = ?4, e.phone_number = ?3, e.manager = ?4 WHERE e.id = ?5", nativeQuery = true)
//    boolean updateEmployee(Employee employee);
//

}
