package gersgarage2.GersGarage2.controllers;

import gersgarage2.GersGarage2.dto.BookingDto;
import gersgarage2.GersGarage2.enumerates.BookingStatus;
import gersgarage2.GersGarage2.enumerates.ServiceType;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Staff;
import gersgarage2.GersGarage2.models.Vehicle;
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
import java.time.LocalDate;
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
        model.addAttribute("staffList", staffList);
        return "admin/addBooking";
    }

    @PostMapping("/addBooking")
    public ModelAndView saveBooking(@ModelAttribute("booking") BookingDto bookingDto, Model model) {
        ModelAndView mv = new ModelAndView("admin/addBooking");

        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("client", clientList);
        List<Staff> staffList = staffRepository.findAll();
        model.addAttribute("staffList", staffList);

        if (bookingDto.getDateService() == null || bookingDto.getDateService().trim().isEmpty()) {
            mv.addObject("errorDate", "Please provide a date to register a new booking with us");
            return mv;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = LocalDate.parse(bookingDto.getDateService());

        if (selectedDate.isBefore(currentDate.plusDays(1))) {
            mv.addObject("errorDate", "Selected date must be at least one day from today.");
            return mv;
        }

        if (!bookingService.isTimeSlotAvailable(bookingDto)) {
            mv.addObject("error", "Selected time slot is not available. Please choose a different time.");
            return mv;
        }

        bookingService.save(bookingDto);
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
    public ModelAndView editBooking(@PathVariable("id") Integer id, Model model) {
        ModelAndView mv = new ModelAndView("admin/editBookings");

        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("client", clientList);
        List<Staff> staffList = staffRepository.findAll();
        model.addAttribute("staff", staffList);
        model.addAttribute("serviceTypes", ServiceType.values());
        model.addAttribute("BookingStatus", BookingStatus.values());

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        mv.addObject("booking", booking);
        return mv;
    }

    @PostMapping("/editBookings")
    public ModelAndView saveEditBooking(Booking booking){
        System.out.println("Editing booking: " + booking.toString());
        ModelAndView mv = new ModelAndView("admin/editBookings");
        bookingRepository.save(booking);
        return mv;
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
        model.addAttribute("staffList", staffList);
        return "client/addBooking-Client";
    }

    @PostMapping("/addBooking-Client")
    public ModelAndView saveBookingClient(@ModelAttribute("booking") BookingDto bookingDto, Principal principal, Model model) {
        ModelAndView mv = new ModelAndView("client/addBooking-Client");

        List<Staff> staffList = staffRepository.findAll();
        model.addAttribute("staffList", staffList);

        if (bookingDto.getDateService() == null || bookingDto.getDateService().trim().isEmpty()) {
            mv.addObject("errorDate", "Please provide a date to register a new booking with us");
            return mv;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = LocalDate.parse(bookingDto.getDateService());

        if (selectedDate.isBefore(currentDate.plusDays(1))) {
            mv.addObject("errorDate", "Selected date must be at least one day from today.");
            return mv;
        }

        if (!bookingService.isTimeSlotAvailable(bookingDto)) {
            mv.addObject("error", "Selected time slot is not available. Please choose a different time.");
            return mv;
        }

        String clientEmail = principal.getName();
        Client client = clientRepository.findByEmail(clientEmail);
        bookingDto.setClient(client);
        bookingDto.setStatus(BookingStatus.BOOKED);
        bookingService.save(bookingDto);

        mv.addObject("message", "Registered successfully!");

        return mv;
    }

    @GetMapping("/deleteBooking-Client/{id}")
    public ModelAndView deleteBookingClient(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/listBooking-Client");
        bookingRepository.deleteById(id);
        return mv;
    }

    @GetMapping("/editBooking-Client/{id}")
    public ModelAndView editBookingClient(@PathVariable("id") Integer id, Model model) {
        ModelAndView mv = new ModelAndView("client/editBooking-Client");
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("client", clientList);
        List<Staff> staffList = staffRepository.findAll();
        model.addAttribute("staff", staffList);

        mv.addObject("booking", booking);
        return mv;
    }

    @PostMapping("/editBooking-Client/")
    public ModelAndView saveEditBookingClient(Booking booking){
        System.out.println("Editing booking: " + booking.toString());
        ModelAndView mv = new ModelAndView("client/editBooking-Client");
        bookingRepository.save(booking);
        return mv;
    }
}
