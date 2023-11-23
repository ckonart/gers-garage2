package gersgarage2.GersGarage2.controllers;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.dto.VehicleDto;
import gersgarage2.GersGarage2.enumerates.Role;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Vehicle;
import gersgarage2.GersGarage2.repositories.BookingRepository;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import gersgarage2.GersGarage2.repositories.VehicleRepository;
import gersgarage2.GersGarage2.service.ClientService;
import gersgarage2.GersGarage2.service.VehicleService;
import gersgarage2.GersGarage2.util.UploadUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private BookingRepository bookingRepository;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("client") ClientDto clientDto){
        clientDto.setRole(Role.CLIENT);
        return "/register";
    }

    @PostMapping("/registration")
    public ModelAndView saveUser(@ModelAttribute("client") ClientDto clientDto, @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("/register");

        String imgFilePath = UploadUtil.uploadImg(file);

        if (imgFilePath != null) {
            clientDto.setImg(file.getOriginalFilename());
            mv.addObject("message", "File upload successfully");
        } else {
            mv.addObject("message", "File upload failed");
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
        System.out.println("Role: " + clientDto.getRole());

        mv.addObject("message", "Registered successfully!");

        return mv;
    }

    @GetMapping("/userpage")
    public String userpage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "client/userpage";
    }

    @GetMapping("/personalInfo")
    public String personalInfo(Model model, Principal principal){
        String username = principal.getName();
        Client client = clientRepository.findByEmail(username);
        model.addAttribute("client", client);

        return "client/personalInfo";
    }

    @GetMapping("/bookingPage-Client")
    public String bookingPageClient(Model model, Principal principal){
        String username = principal.getName();
        Client client = clientRepository.findByEmail(username);
        model.addAttribute("client", client);

        return "client/bookingPage-Client";
    }

    @GetMapping("/editClientProfile/{id}")
    public ModelAndView editClient(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("client/editClientProfile");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        mv.addObject("password", client.getPassword());
        mv.addObject("confirmPassword", client.getConfirmPassword());
        mv.addObject("client", client);
        return mv;
    }

    @PostMapping("/edit-ClientProfile")
    public ModelAndView editClient(Client client){
        System.out.println("Editing client: " + client.toString());
        ModelAndView mv = new ModelAndView("client/editClientProfile");
        clientRepository.save(client);
        mv.addObject("message", "Registered successfully!");
        return mv;
    }

    @GetMapping("/editClientPassword/{id}")
    public ModelAndView editClientPassword(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("client/editClientPassword");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        mv.addObject("client", client);
        return mv;
    }

    @PostMapping("/edit-ClientPassword")
    public ModelAndView editClientPassword(@RequestParam("id") Integer id, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        ModelAndView mv = new ModelAndView("client/editClientPassword");
        clientService.updatePassword(id, password);
        clientService.updateConfirmPassword(id, confirmPassword);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        mv.addObject("client", client);

        mv.addObject("message", "Password changed successfully!");
        return mv;
    }

    @GetMapping("/editBooking-Client/{id}")
    public ModelAndView editBookingClient(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("client/editBooking-Client");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        mv.addObject("client", client);
        return mv;
    }

    @PostMapping("/edit-BookingClient")
    public ModelAndView editBookingClient(Client client){
        System.out.println("Editing client: " + client.toString());
        ModelAndView mv = new ModelAndView("client/editBooking-Client");
        clientRepository.save(client);
        return mv;
    }

    @GetMapping("/editVehicleClient/{id}")
    public ModelAndView editVehiclesClient(@PathVariable("id") Integer id, Model model) {
        ModelAndView mv = new ModelAndView("client/editVehicleClient");
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("client", clientList);

        mv.addObject("vehicle", vehicle);
        return mv;
    }

    @PostMapping("/editVehicleClient")
    public ModelAndView saveEditVehiclesClient(Vehicle vehicle){
        System.out.println("Editing vehicle: " + vehicle.toString());
        ModelAndView mv = new ModelAndView("client/editVehicleClient");
        vehicleRepository.save(vehicle);
        return mv;
    }

    @GetMapping("/addVehicles-Client")
    public ModelAndView newVehicle(@ModelAttribute("vehicle") VehicleDto vehicleDto, Model model, Principal principal){
        ModelAndView mv = new ModelAndView("client/addVehicle-Client");

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

    @PostMapping("/new-VehicleClient")
    public ModelAndView saveNewVehicle(@ModelAttribute("vehicle") VehicleDto vehicleDto, Model model) {
        ModelAndView mv = new ModelAndView("client/addVehicle-Client");
        vehicleService.save(vehicleDto);
        mv.addObject("message", "Registered successfully!");

        return mv;
    }

}
