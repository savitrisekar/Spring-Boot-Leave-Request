/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.repository;

import metrodatamii.metrodatamii.entities.Job;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arif Fridasari
 */
public interface IJobRepository extends CrudRepository<Job, String> {
    
}
