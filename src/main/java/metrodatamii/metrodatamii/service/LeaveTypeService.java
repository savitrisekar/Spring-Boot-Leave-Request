/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import metrodatamii.metrodatamii.entities.LeaveType;
import metrodatamii.metrodatamii.repository.ILeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arif Fridasari
 */
@Service
public class LeaveTypeService {

    @Autowired
    private ILeaveTypeRepository leaveTypeRepository;

    public Iterable<LeaveType> findAllType() {
        return leaveTypeRepository.findAll();
    }
}
