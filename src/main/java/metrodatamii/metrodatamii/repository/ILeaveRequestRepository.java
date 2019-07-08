/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.repository;

import java.util.List;
import metrodatamii.metrodatamii.entities.LeaveRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Arif Fridasari
 */
public interface ILeaveRequestRepository extends CrudRepository<LeaveRequest, String> {

    @Query(value = "SELECT * FROM leave_request lr  WHERE lr.status = 1", nativeQuery = true)
    List<LeaveRequest> getByStatusPending();

    @Query(value = "SELECT * FROM leave_request WHERE id = ?1", nativeQuery = true)
    List<LeaveRequest> getLrById(String id);

    @Query(value = "UPDATE leave_request lr  SET lr.status = 2 WHERE lr.id =?1", nativeQuery = true)
    String approved(String id);

}
