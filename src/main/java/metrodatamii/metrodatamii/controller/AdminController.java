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

/**
 *
 * @author Arif Fridasari
 */
@Controller
public class AdminController {

    @Autowired
    private JobService jobService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "indexUser";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/employee")
    public String employee(Model model) {
        model.addAttribute("dataEmployee", employeeService.findAllEmployee());
        return "employee";
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("dataAccount", accountService.findAllAccount());
        return "account";
    }

    @GetMapping("/job")
    public String job(Model model) {

        model.addAttribute("dataJob", jobService.findAllJob());
        return "job";
    }

    @GetMapping("/listrequest")
    public String listreq(Model model) {
        model.addAttribute("dataLR", leaveRequestService.findAllLR());
        return "listrequest";
    }

    @GetMapping("/pending")
    public String pending(Model model) {
        model.addAttribute("dataLR", leaveRequestService.findAllLR());
        return "pending";
    }

}
