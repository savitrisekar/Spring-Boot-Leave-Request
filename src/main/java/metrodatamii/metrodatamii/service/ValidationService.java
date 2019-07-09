/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import java.util.List;
import metrodatamii.metrodatamii.entities.Validation;
import metrodatamii.metrodatamii.repository.IValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sekar Ayu Safitri
 */
@Service
public class ValidationService {
    @Autowired
    private IValidationRepository validRepository;

    public Iterable<Validation> findAllValidation() {
        return validRepository.findAll();
    }
    
//    public List<Validation> getAll() {
//        return validRepository.getAll();
//    }
}
