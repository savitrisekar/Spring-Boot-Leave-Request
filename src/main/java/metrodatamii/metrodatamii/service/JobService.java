/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import metrodatamii.metrodatamii.entities.Job;
import metrodatamii.metrodatamii.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arif Fridasari
 */

@Service
public class JobService {
    
    @Autowired
    private IJobRepository jobRepository;
    
    public Iterable<Job> findAllJob(){
        return jobRepository.findAll();
    }
    
}
