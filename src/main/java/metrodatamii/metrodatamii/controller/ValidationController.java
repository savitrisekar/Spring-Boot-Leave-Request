/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.controller;

import java.util.Date;
import metrodatamii.metrodatamii.config.EmailConfig;
import metrodatamii.metrodatamii.entities.Employee;
import metrodatamii.metrodatamii.entities.Validation;
import metrodatamii.metrodatamii.repository.IEmployeeRepository;
import metrodatamii.metrodatamii.repository.IValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sekar Ayu Safitri
 */
@Controller
@RequestMapping(value = {"/forget"})
public class ValidationController {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private IValidationRepository validRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;
    
    @GetMapping("/password")
    public String index() {
        return "forget/password/index";
    }
    
    @GetMapping("/password/send")
    public String sendForgetPassword(String email) {
        String result = "redirect:/forget/password?error";
        String token = emailConfig.createToken(emailConfig.randomBetweenTwoNumber(150, 210));

        Employee employee = validRepository.getIdByEmail(email);

        String subject = "Forget Password";
        String message = templateEmail(employee.getFirstName(), token);

        if (emailConfig.sendEmail(email, subject, message)) {
            result = "forget/password/success";
        }

        Validation validation = new Validation(employee.getId(), "false", token);
        validRepository.save(validation);
        return result;
    }

    @GetMapping("/password/reset")
    public String sendResetPassword(@RequestParam("token") String token, Model model) {
        String result = "forget/password/reset-password";
        if(validRepository.getEmailByToken(token).isEmpty()){
            result = "redirect:/forget/password/reset/expired";
        }
        model.addAttribute("token", token);
        return result;
    }
    

    @PostMapping("/password/reset")
    public String sendResetPassword(String token, String newpass, String repass, Model model) {
        String result = "redirect:/forget/password/reset?error";
        String email = "";
        String error = "";


        if (newpass.equals(repass)) {
            if (newpass.contains(" ")) {
                error = "whitespace";
                result = "redirect:/forget/password/reset?error="+error+"&token="+ token;
            } else {
                email = validRepository.getEmailByToken(token).get(0).toString();
                validRepository.updatePassword(email, new BCryptPasswordEncoder().encode(newpass));
                validRepository.updateToken(token, "true");
                result = "redirect:/?email="+email+"&success=reset";
            }
        } else {
            error = "different";
            result = "redirect:/forget/password/reset?error="+error+"&token="+ token;
        }
        return result;
    }
    
    @GetMapping("/password/reset/expired")
    public String getExpiredToken() {
        String result = "forget/password/reset-expired-token";
        return result;
    }

    public String templateEmail(String name, String token) {
        String link = "http://localhost:8070/forget/password/reset?token=" + token;
        String host = "http://localhost:8070";
        String imgUrl = "https://www.metrodata.co.id/web/images/business/2014-08-20-112626.png";
        String template = "<div style=\"background:#fff; border:2px solid #cccccc; padding:40px\">\n"
                + "<p style=\"text-align:center\"><span style=\"font-size:14px\"><a href=\"" + host + "\" target=\"_blank\"><img alt=\"\" src=\"" + imgUrl + "\" style=\"height:200px; background:#fff\" /></a></span></p>\n"
                + "<h1><strong>Pemulihan Kata Sandi</strong></h1>\n"
                + "<p>Hai " + name + ",</p>\n"
                + "<p>Kami menerima permintaan untuk mengatur ulang kata sandi Evaluation Bootcamp.</p>\n"
                //                + "<p>Masukkan kode pemulihan kata sandi berikut ini :</p>\n"
                //                + "<div style=\"background:#eee; border:1px solid #cccccc; padding:5px 10px;font-size:40px\">\n"
                //                + "<center><h1><strong>123456</strong></h1></center>\n"
                //                + "</div>\n"
                //                + "<p>Atau dengan klik tombol berikut ini :</p>\n"
                + "<center><a href=\"" + link + "\" target=\"_blank\"><h1 style=\"background:linear-gradient(#ff9900, #ffaf00); border-radius:5px; color:#ffffff; display:inline-block; padding:8px 20px\"><strong>Ubah Kata Sandi</strong></h1></a></center>\n"
//                + "<p>Atau dengan link berikut :</p>\n"
//                + "<a href=\"" + link + "\" target=\"_blank\"><strong>Evaluation Bootcamp</strong></a>\n"
                + "<p>&nbsp;</p>\n"
                + "<p>Dimohon untuk menjaga kerahasiaan kata sandi anda.</p>\n"
                + "<p>&nbsp;</p>\n"
                + "<p>Terimakasih atas perhatiannya,</p>\n"
                + "<p><strong>Evaluation Bootcamp</strong></p>\n"
                + "<p>&nbsp;</p>\n"
                + "<p><strong>nb :&nbsp;</strong>apabila terjadi suatu kesalahan dimohon untuk menghubungi admin</p>\n"
                + "</div>";
        return template;
    }
}
