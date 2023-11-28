package gersgarage2.GersGarage2.controllers;

import gersgarage2.GersGarage2.dto.BookingDto;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Staff;
import gersgarage2.GersGarage2.repositories.BookingRepository;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import gersgarage2.GersGarage2.repositories.StaffRepository;
import gersgarage2.GersGarage2.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/services")
    public String services() {
        return "admin/services";
    }

    @GetMapping("/addBooking")
    public String booking(@ModelAttribute("booking") BookingDto bookingDto, Model model){
        List<Client> clientList = clientRepository.findAll();
        List<Staff> staffList = staffRepository.findAll();
        model.addAttribute("client", clientList);
        model.addAttribute("staff", staffList);
        return "admin/addBooking";
    }

    @PostMapping("/new-Booking")
    public ModelAndView saveBooking(@ModelAttribute("booking") BookingDto bookingDto) {
        ModelAndView mv = new ModelAndView("admin/addBooking");

        if (!bookingService.isTimeSlotAvailable(bookingDto)) {
            mv.addObject("error", "Selected time slot is not available. Please choose a different time.");
            return mv;
        }
        bookingService.save(bookingDto);

        // Debugging - print clientDto fields
        System.out.println("Cost: " + bookingDto.getCostService());
        System.out.println("Start date: " + bookingDto.getDateService());
        System.out.println("Start time: " + bookingDto.getStartTime());
        System.out.println("Details: " + bookingDto.getDetails());
        System.out.println("Service type: " + bookingDto.getServiceType());
        System.out.println("Status: " + bookingDto.getStatus());

        mv.addObject("message", "Registered successfully!");

        return mv;
    }

    @GetMapping("/listBookings")
    public ModelAndView bookingsList(){
        ModelAndView mv = new ModelAndView("admin/listBookings");
        mv.addObject("bookingList", bookingRepository.findAllBookings());
        return mv;
    }

    @GetMapping("/editBookings/{id}")
    public ModelAndView editBooking(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("admin/editBookings");
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        mv.addObject("booking", booking);
        return mv;
    }

    @PostMapping("/edit-Booking")
    public ModelAndView editAdmin(Booking booking){
        System.out.println("Editing booking: " + booking.toString());
        ModelAndView mv = new ModelAndView("admin/editBookings");
        bookingRepository.save(booking);
        return bookingsList();
    }

    @GetMapping("/deleteBookings/{id}")
    public ModelAndView deleteBooking(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/listBookings");
        bookingRepository.deleteById(id);
        return mv;
    }

    @GetMapping("/listBooking-Client")
    public ModelAndView bookingsListClient(Principal principal){
        ModelAndView mv = new ModelAndView("client/listBooking-Client");

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

    @GetMapping("/addBooking-Client")
    public String bookingClient(@ModelAttribute("booking") BookingDto bookingDto, Model model){
        List<Staff> staffList = staffRepository.findAll();
        model.addAttribute("staff", staffList);
        return "client/addBooking-Client";
    }

    @PostMapping("/new-BookingClient")
    public ModelAndView saveBookingClient(@ModelAttribute("booking") BookingDto bookingDto, Principal principal) {
        ModelAndView mv = new ModelAndView("client/addBooking-Client");

        String clientEmail = principal.getName();
        Client client = clientRepository.findByEmail(clientEmail);
        bookingDto.setClient(client);
        bookingService.save(bookingDto);

        mv.addObject("message", "Registered successfully!");

        return mv;
    }
}
