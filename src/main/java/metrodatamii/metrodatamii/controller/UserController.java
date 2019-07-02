/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.controller;

import metrodatamii.metrodatamii.entities.LeaveRequest;
import metrodatamii.metrodatamii.entities.Status;
import metrodatamii.metrodatamii.repository.ILeaveRequestRepository;
import metrodatamii.metrodatamii.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Arif Fridasari
 */
@Controller
public class UserController {

    @Autowired
    private LeaveTypeService typeService;

    @Autowired
    private ILeaveRequestRepository leaveRequestRepository;

    @GetMapping("/user")
    public String user(Model model) {
        return "indexUser";
    }

    @GetMapping("/user/information")
    public String information(Model model) {
        return "user_information";
    }

    @PostMapping("/addRequest")
    public String addRequest(LeaveRequest leaveRequest) {
        leaveRequest.setId("0");
        Status status = new Status();
        status.setId(1);
        leaveRequest.setStatus(status);
        leaveRequestRepository.save(leaveRequest);
        return "redirect:/user";
    }

    @GetMapping("/user/request")
    public String userHistory(Model model) {
        model.addAttribute("dataType", typeService.findAllType());
        model.addAttribute("dataLR", leaveRequestRepository.getByStatusPending());
        return "user_request";
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        return "user_profile";
    }
}
