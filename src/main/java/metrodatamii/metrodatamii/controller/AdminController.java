/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.controller;

import javax.validation.Valid;
import metrodatamii.metrodatamii.entities.Employee;
import metrodatamii.metrodatamii.entities.Job;
import metrodatamii.metrodatamii.entities.LeaveRequest;
import metrodatamii.metrodatamii.entities.Role;
import metrodatamii.metrodatamii.entities.Status;
import metrodatamii.metrodatamii.repository.IEmployeeRepository;
import metrodatamii.metrodatamii.repository.IJobRepository;
import metrodatamii.metrodatamii.repository.ILeaveRequestRepository;
import metrodatamii.metrodatamii.repository.IRoleRepository;
import metrodatamii.metrodatamii.service.AccountService;
import metrodatamii.metrodatamii.service.EmployeeService;
import metrodatamii.metrodatamii.service.JobService;
import metrodatamii.metrodatamii.service.LeaveRequestService;
import metrodatamii.metrodatamii.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Arif Fridasari
 */
@Controller
public class AdminController {

    @Autowired
    private JobService jobService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IJobRepository jobRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private ILeaveRequestRepository leaveRequestRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

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
        model.addAttribute("dataEmployee", employeeService.getAll());
        return "employee";
    }

    @PostMapping("/employeeEdit/id")
    public String updateEmp(Employee employee) {
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/findEmp")
    @ResponseBody
    public Employee employee(String id) {
        Employee emp = new Employee(
                employeeRepository.getEmployeeById(id).get(0).getId(),
                employeeRepository.getEmployeeById(id).get(0).getFirstName(),
                employeeRepository.getEmployeeById(id).get(0).getLastName(),
                employeeRepository.getEmployeeById(id).get(0).getEmail(),
                employeeRepository.getEmployeeById(id).get(0).getSalary(),
                employeeRepository.getEmployeeById(id).get(0).getPhoneNumber(),
                employeeRepository.getEmployeeById(id).get(0).getManager().getId()
        );
        return emp;
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("dataAccount", accountService.findAllAccount());
        model.addAttribute("dataEmployee", employeeService.getAll());
        return "account";
    }

    @GetMapping("/job")
    public String job(Model model) {
        model.addAttribute("dataJob", jobService.getAll());
        model.addAttribute("dataEmployee", employeeService.getAll());
        return "job";
    }

    @PostMapping("/addJob")
    public String addJob(Job job) {
        job.setId("0");
        job.setIsDelete("false");
        jobRepository.save(job);
        return "redirect:/job";
    }

    @PostMapping("/jobEdit/{id}")
    public String updateJob(@PathVariable("id") String id, @Valid Job job) {
        job.setIsDelete("false");
        jobRepository.save(job);
        return "redirect:/job";
    }

    @GetMapping("/jobDelete/{id}")
    public String softDelete(@PathVariable("id") String id, @Valid Job job) {
        jobRepository.softDelete(id);
        return "redirect:/job";
    }

    @GetMapping("/listrequest")
    public String listreq(Model model) {
        model.addAttribute("dataLR", leaveRequestRepository.getByStatusPending());
        return "listrequest";
    }

//    @RequestMapping("/leaveApproval")
//    public String leaveRequest(String id) {
//        leaveRequestRepository.softDelete("id");
//        return "listrequest";
//    }
//    @GetMapping("/leaveApproval")
//    @ResponseBody
//    public LeaveRequest leaveRequest(String id) {
//        LeaveRequest lr = new LeaveRequest();
//        lr.setId(id);
//        leaveRequestRepository.softDelete(id);
//        return lr;
//    }
    @PostMapping("/leaveApproval/{id}")
    @ResponseBody
    public String upadateData(@PathVariable("id") String id, @Valid LeaveRequest leaveRequest) {
        Status status = new Status();
        status.setId(2);
        leaveRequest.setStatus(status);
        leaveRequestRepository.save(leaveRequest);
        return "redirect:/listrequest";
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("dataLR", leaveRequestService.findAllLR());
        return "history";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(Employee employee) {
        employee.setId("0");
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/role")
    public String role(Model model) {
        model.addAttribute("dataRole", roleService.findAllRole());
        model.addAttribute("dataEmployee", employeeService.getAll());
        return "role";
    }

    @PostMapping("/addRole")
    public String addRole(Role role) {
        role.setId("0");
        role.setIsDelete("false");
        roleRepository.save(role);
        return "redirect:/role";
    }

}
