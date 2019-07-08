/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import metrodatamii.metrodatamii.entities.Account;
import metrodatamii.metrodatamii.entities.Employee;
import metrodatamii.metrodatamii.entities.Job;
import metrodatamii.metrodatamii.entities.LeaveRequest;
import metrodatamii.metrodatamii.entities.LeaveType;
import metrodatamii.metrodatamii.entities.Role;
import metrodatamii.metrodatamii.entities.Status;
import metrodatamii.metrodatamii.repository.IAccountRepository;
import metrodatamii.metrodatamii.repository.IEmployeeRepository;
import metrodatamii.metrodatamii.repository.IJobRepository;
import metrodatamii.metrodatamii.repository.ILeaveRequestRepository;
import metrodatamii.metrodatamii.repository.IRoleRepository;
import metrodatamii.metrodatamii.service.AccountService;
import metrodatamii.metrodatamii.service.EmployeeService;
import metrodatamii.metrodatamii.service.JobService;
import metrodatamii.metrodatamii.service.LeaveRequestService;
import metrodatamii.metrodatamii.service.LeaveTypeService;
import metrodatamii.metrodatamii.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Arif Fridasari
 */
@Controller
public class AdminController {

    @Autowired
    private LeaveTypeService typeService;

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
    private IAccountRepository accountRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private ILeaveRequestRepository leaveRequestRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @RequestMapping(value = {"/", "", "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "index";
    }
//    @GetMapping("/")
//    public String login(Model model) {
//        return "login";
//    }

//    @GetMapping("/home")
//    public String home(Model model) {
//        return "index";
//    }
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

    @PostMapping("/employeeDel/id")
    public String softDelete(Employee employee) {
        employee.setIsDelete("true");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/findEmp")
    @ResponseBody
    public Employee employee(String id) {
        Employee emp = employeeRepository.getEmployeeById(id);
        emp = new Employee(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getSalary(),
                emp.getPhoneNumber(),
                emp.getManager().getId()
        );
        return emp;
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("dataAccount", accountService.getAll());
        model.addAttribute("dataEmployee", employeeService.getAll());
        return "account";
    }

   @PostMapping("/addDataAcc")
    public String account(Account account, String password) {
        account.setIsDelete("false");
        account.setIsActive("false");
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        accountRepository.save(account);
        return "redirect:/account";
    }

    @PostMapping("/accountDelete/{id}")
    public String softDelete(@PathVariable("id") String id, @Valid Account account) {
        account.setIsDelete("true");
        account.setIsActive("false");
        accountRepository.save(account);
        return "redirect:/account";
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

    @PostMapping("/jobDelete/{id}")
    public String softDelete(@PathVariable("id") String id, @Valid Job job) {
        job.setIsDelete("true");
        jobRepository.save(job);
        return "redirect:/job";
    }

    @GetMapping("/listrequest")
    public String listreq(Model model) {
        model.addAttribute("dataLR", leaveRequestRepository.getByStatusPending());
        model.addAttribute("dataEmployee", employeeService.getAll());
        model.addAttribute("dataType", typeService.findAllType());
        return "listrequest";
    }

//    @GetMapping("/leaveApproval/{id}")
//    public String upadateData(Model model) {
//        Status status = new Status();
//        status.setId(2);
//        leaveRequest.setStatus(status);
//        LeaveType lt = new LeaveType();
//        lt.setId("T-0001");
//        leaveRequest.setType(lt);
//        Employee e = new Employee();
//        e.setId("10002");
//        leaveRequest.setRequester(e);
//        model.addAttribute("dataEmployee", employeeService.getAll());
//        model.addAttribute("dataType", typeService.findAllType());
//        leaveRequestRepository.save(leaveRequest);
//        return "redirect:/listrequest";
//    }
    @PostMapping("/leaveApproval/{id}")
    public String upadateData(@PathVariable("id") String id, @Valid LeaveRequest leaveRequest
    ) {

        Status status = new Status();
        status.setId(2);
        leaveRequest.setStatus(status);
//        LeaveType lt = new LeaveType();
//        lt.setId("T-0001");
//        leaveRequest.setType(lt);
//        Employee e = new Employee();
//        e.setId("10002");
//        leaveRequest.setRequester(e);

        leaveRequestRepository.save(leaveRequest);
        return "redirect:/listrequest";
    }

    @PostMapping("/leaveReject/{id}")
    public String rejectData(@PathVariable("id") String id, @Valid LeaveRequest leaveRequest
    ) {

        Status status = new Status();
        status.setId(3);
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

    @GetMapping("/403")
    public String error403() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error500() {
        return "error/500";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error/400";
            }
        }
        return "error/error";
    }

    @GetMapping("/home/profile")
    public String profile(Model model) {
        return "profile";
    }

}
