/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.controller;

import metrodatamii.metrodatamii.service.AccountService;
import metrodatamii.metrodatamii.service.EmployeeService;
import metrodatamii.metrodatamii.service.JobService;
import metrodatamii.metrodatamii.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Arif Fridasari
 */
@Controller
public class UserController {

    @Autowired
    private JobService jobService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

//    @GetMapping("/user")
//    public String user(Model model) {
//        return "index_user";
//    }

//    @GetMapping("/employee")
//    public String employee(Model model) {
//        model.addAttribute("dataEmployee", employeeService.findAllEmployee());
//        return "employee";
//    }
//
//    @GetMapping("/account")
//    public String account(Model model) {
//        model.addAttribute("dataAccount", accountService.findAllAccount());
//        return "account";
//    }
//
//    @GetMapping("/job")
//    public String job(Model model) {
//
//        model.addAttribute("dataJob", jobService.findAllJob());
//        return "job";
//    }
//
//    @GetMapping("/listrequest")
//    public String listreq(Model model) {
//
//        model.addAttribute("dataLR", leaveRequestService.findAllLR());
//        return "listrequest";
//    }
}
