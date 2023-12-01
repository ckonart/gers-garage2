package gersgarage2.GersGarage2.controllers;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.dto.VehicleDto;
import gersgarage2.GersGarage2.enumerates.Role;
import gersgarage2.GersGarage2.models.*;
import gersgarage2.GersGarage2.repositories.*;
import gersgarage2.GersGarage2.service.*;
import gersgarage2.GersGarage2.util.UploadUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@Validated
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
    private UserDetailsService userDetailsService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;


    @GetMapping("/adminpage")
    public String adminpage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin/adminpage";
    }

    @GetMapping("/registration-user")
    public String getRegistrationPage(@ModelAttribute("user") ClientDto clientDto){

        return "admin/registerUser";
    }

    @PostMapping("/registration-user")
    public ModelAndView saveUser(@ModelAttribute("user") @Valid ClientDto clientDto, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("admin/registerUser");

        try {
            if (bindingResult.hasErrors()) {
                return mv;
            }
            if (!clientDto.getPassword().equals(clientDto.getConfirmPassword())) {
                mv.addObject("passwordMismatch", "Passwords do not match");
                return mv;
            }

            String imgFilePath = UploadUtil.uploadImg(file);

            if (imgFilePath != null) {
                clientDto.setImg(file.getOriginalFilename());
                mv.addObject("message", "File upload successfully");
            } else {
                mv.addObject("message", "File upload failed");
            }

            if (clientDto.getRole() == Role.STAFF || (clientDto.getPassword().equals(clientDto.getConfirmPassword()))) {
                staffService.save(clientDto);
            } else if (clientDto.getRole() == Role.CLIENT) {
                clientService.save(clientDto);
            } else if (clientDto.getRole() == Role.ADMIN) {
                adminService.save(clientDto);
            }

            mv.addObject("message", "Registered successfully!");
        } catch (DataIntegrityViolationException e) {
            mv.addObject("error", "Email already exists in our system. Please choose a different email or click in 'forget password'.");
        }
        return mv;
    }

    @GetMapping("/profiles")
    public String profiles() {
        return "admin/profiles";
    }

    @GetMapping("/listAll-staff")
    public ModelAndView staffList(){
        ModelAndView mv = new ModelAndView("admin/listProfilesStaff");
        mv.addObject("staff", staffRepository.findAll());
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
        ModelAndView mv = new ModelAndView("redirect:/listAll-staff");
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
        mv.addObject("client", clientRepository.findAll());
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

    @GetMapping("/addVehicle")
    public ModelAndView newVehicle(@ModelAttribute("vehicle") VehicleDto vehicleDto, Model model, Principal principal){
        ModelAndView mv = new ModelAndView("admin/addVehicleForClient");

        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("client", clientList);

        String clientEmail = principal.getName();
        Client client = clientRepository.findByEmail(clientEmail);
        if(client != null){
            List<Booking> bookingList = bookingRepository.findByClient(client);
            mv.addObject("bookingList", bookingList);
        } else {
            mv.addObject("bookingList", Collections.emptyList());
        }
        return mv;
    }

    @PostMapping("/new-Vehicle")
    public ModelAndView saveNewVehicle(@ModelAttribute("vehicle") VehicleDto vehicleDto, Model model) {
        ModelAndView mv = new ModelAndView("admin/addVehicleForClient");
        vehicleService.save(vehicleDto);
        mv.addObject("message", "Registered successfully!");

        return mv;
    }
    @GetMapping("/listVehicle")
    public ModelAndView vehicleListClient(){
        ModelAndView mv = new ModelAndView("admin/listVehicle");
        mv.addObject("vehicle", vehicleRepository.findAll());
        return mv;
    }
    @GetMapping("/editVehicle/{id}")
    public ModelAndView editVehicles(@PathVariable("id") Integer id, Model model) {
        ModelAndView mv = new ModelAndView("admin/editVehicle");
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("client", clientList);

        mv.addObject("vehicle", vehicle);
        return mv;
    }

    @PostMapping("/editVehicle")
    public ModelAndView saveEditVehicles(Vehicle vehicle){
        System.out.println("Editing vehicle: " + vehicle.toString());
        ModelAndView mv = new ModelAndView("admin/editVehicle");
        vehicleRepository.save(vehicle);
        return mv;
    }
    @GetMapping("/deleteVehicle/{id}")
    public ModelAndView deleteVehicle(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/listVehicles");
        vehicleRepository.deleteById(id);
        return mv;
    }
}
