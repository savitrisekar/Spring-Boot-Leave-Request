/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.controller;

import metrodatamii.metrodatamii.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Arif Fridasari
 */
@Controller
public class MainController {

    @Autowired
    private JobService jobService;

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/employee")
    public String employee(Model model) {
        return "employee";
    }

    @GetMapping("/account")
    public String account(Model model) {
        return "account";
    }

    @GetMapping("/job")
    public String job(Model model) {
        model.addAttribute("dataJob", jobService.findAllJob());
        return "job";
    }

}
