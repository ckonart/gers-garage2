package gersgarage2.GersGarage2.controllers;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.enumerates.Role;
import gersgarage2.GersGarage2.models.Admin;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Staff;
import gersgarage2.GersGarage2.repositories.AdminRepository;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import gersgarage2.GersGarage2.repositories.StaffRepository;
import gersgarage2.GersGarage2.service.AdminService;
import gersgarage2.GersGarage2.service.ClientService;
import gersgarage2.GersGarage2.service.StaffService;
import gersgarage2.GersGarage2.util.UploadUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/registration-user")
    public String getRegistrationPage(@ModelAttribute("user") ClientDto clientDto){

        return "admin/registerUser";
    }

    @PostMapping("/registration-user")
    public ModelAndView saveUser(@ModelAttribute("user") ClientDto clientDto, @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("admin/registerUser");

        String imgFilePath = UploadUtil.uploadImg(file);

        if (imgFilePath != null) {
            clientDto.setImg(file.getOriginalFilename());
            mv.addObject("message", "File upload successfully");
        } else {
            mv.addObject("message", "File upload failed");
        }

        if (clientDto.getRole() == Role.STAFF) {
            staffService.save(clientDto);
        } else if (clientDto.getRole() == Role.CLIENT) {
            clientService.save(clientDto);
        } else if (clientDto.getRole() == Role.ADMIN) {
            adminService.save(clientDto);
        }

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
        System.out.println("Role: " + clientDto.getRole());

        mv.addObject("message", "Registered successfully!");

        return mv;
    }

    @GetMapping("/profiles")
    public String profiles() {
        return "admin/profiles";
    }

    @GetMapping("/listAll-staff")
    public ModelAndView staffList(){
        ModelAndView mv = new ModelAndView("admin/listProfilesStaff");
        mv.addObject("Staff", staffRepository.findAll());
        return mv;
    }

    @GetMapping("/editProfilesStaff/{id}")
    public ModelAndView editStaff(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("admin/editProfilesStaff");
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));
        mv.addObject("staff", staff);
        return mv;
    }

    @PostMapping("/edit-Staff")
    public ModelAndView editStaff(Staff staff){
        System.out.println("Editing staff: " + staff.toString());
        ModelAndView mv = new ModelAndView("admin/editProfilesClients");
        staffRepository.save(staff);
        return staffList();
    }

    @GetMapping("/deleteStaff/{id}")
    public ModelAndView deleteStaff(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/listAll-staff");
        staffRepository.deleteById(id);
        return mv;
    }

    @GetMapping("/listAll-clients")
    public ModelAndView clientsList() {
        ModelAndView mv = new ModelAndView("admin/listProfilesClients");
        mv.addObject("Client", clientRepository.findAll());
        return mv;
    }

    @GetMapping("/deleteClient/{id}")
    public String deleteClients(@PathVariable("id") Integer id) {
        clientRepository.deleteById(id);
        return "redirect:/listAll-clients"; // Redirect back to the list of clients
    }

    @GetMapping("/editProfilesClients/{id}")
    public ModelAndView editClient(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("admin/editProfilesClients");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        mv.addObject("client", client);
        return mv;
    }

    @PostMapping("/edit-Client")
    public ModelAndView editClient(Client client){
        System.out.println("Editing client: " + client.toString());
        ModelAndView mv = new ModelAndView("admin/editProfilesClients");
        clientRepository.save(client);
        return clientsList();
    }

    @GetMapping("/listAll-admin")
    public ModelAndView admList(){
        ModelAndView mv = new ModelAndView("admin/listProfilesAdmin");
        mv.addObject("Admin", adminRepository.findAll());
        return mv;
    }

    @GetMapping("/editProfilesAdmin/{id}")
    public ModelAndView editAdm(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("admin/editProfilesAdmin");
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        mv.addObject("admin", admin);
        return mv;
    }

    @PostMapping("/edit-Admin")
    public ModelAndView editAdmin(Admin admin){
        System.out.println("Editing admin: " + admin.toString());
        ModelAndView mv = new ModelAndView("admin/editProfilesAdmin");
        adminRepository.save(admin);
        return admList();
    }

    @GetMapping("/deleteAdmin/{id}")
    public ModelAndView deleteAdmin(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/listAll-admin");
        adminRepository.deleteById(id);
        return mv;
    }
}
