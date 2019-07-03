/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.service;

import org.springframework.stereotype.Service;
import metrodatamii.metrodatamii.entities.LeaveRequest;
import metrodatamii.metrodatamii.repository.ILeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author KHAIRUL MUNA
 */
@Service
public class LeaveRequestService {

    @Autowired
    private ILeaveRequestRepository leaveRequestRepository;

    public Iterable<LeaveRequest> findAllLR() {
        return leaveRequestRepository.findAll();
    }

//    public void LeaveRequest getById() {
//        return leaveRequestRepository.getById();
//    }

}
