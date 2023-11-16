package gersgarage2.GersGarage2.controllers;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.service.ClientService;
import gersgarage2.GersGarage2.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("client") ClientDto clientDto){
        return "register";
    }

    @PostMapping("/registration")
    public String saveClient(@ModelAttribute("client") ClientDto clientDto, Model model, @RequestParam("file")MultipartFile file){

        String imgFilePath = UploadUtil.uploadImg(file);

        if (imgFilePath != null) {
            clientDto.setImg(file.getOriginalFilename());
            model.addAttribute("message", "File upload successfully");
        } else {
            model.addAttribute("message", "File upload failed");
        }

        clientService.save(clientDto);

        // Debugging - print clientDto fields
        System.out.println("First Name: " + clientDto.getFirstName());
        System.out.println("Last Name: " + clientDto.getLastName());
        System.out.println("Email: " + clientDto.getEmail());
        System.out.println("Password: " + clientDto.getPassword());
        System.out.println("Confirm Pwd: " + clientDto.getConfirmPassword());
        System.out.println("Phone number: " + clientDto.getPhoneNumber());
        System.out.println("Gender: " + clientDto.getGender());
        System.out.println("Dob: " + clientDto.getDob());
        System.out.println("Img: " + clientDto.getImg());

        model.addAttribute("message", "Registered successfully!");
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/userpage")
    public String userpage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "user/userpage";
    }

    @GetMapping("/adminpage")
    public String adminpage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin/adminpage";
    }

    @GetMapping("/staffpage")
    public String staffpage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "staff/staffpage";
    }
}
