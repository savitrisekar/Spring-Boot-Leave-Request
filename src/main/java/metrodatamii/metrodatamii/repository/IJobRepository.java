/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.repository;

import java.util.List;
import metrodatamii.metrodatamii.entities.Job;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arif Fridasari
 */
@Repository
public interface IJobRepository extends CrudRepository<Job, String> {

    @Query(value = "SELECT * FROM job  WHERE is_delete = 'false'", nativeQuery = true)
    List<Job> getAll();
    
//    @Query(value = "SELECT * FROM job j WHERE j.id = 1", nativeQuery = true)
//    public Job findJobId(String id);
//
//    @Modifying
//    @Query(value = "SELECT * FROM job  WHERE id = 1", nativeQuery = true)
//    public void deleteById(String id);

}
