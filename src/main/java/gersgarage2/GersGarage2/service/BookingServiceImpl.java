package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.BookingDto;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.repositories.BookingRepository;
import gersgarage2.GersGarage2.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Booking save(BookingDto bookingDto) {
        Booking booking = convertToBooking(bookingDto);
        return bookingRepository.save(booking);
    }

    private Booking convertToBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setServiceType(bookingDto.getServiceType());
        booking.setStatus(bookingDto.getStatus());
        booking.setDateService(bookingDto.getDateService());
        booking.setStartTime(bookingDto.getStartTime());
        booking.setDetails(bookingDto.getDetails());
        booking.setCostService(bookingDto.getCostService());
        booking.setExtraCost(bookingDto.getExtraCost());
        booking.setTotalCost(bookingDto.getTotalCost());
        booking.setClient(bookingDto.getClient());
        booking.setVehicle(bookingDto.getVehicle());
        booking.setStaff(bookingDto.getStaff());

        return booking;
    }

    public boolean isTimeSlotAvailable(BookingDto newBooking) {

        List<Booking> existingBookings = bookingRepository.findByStaffAndDateService(
                staffRepository.findById(newBooking.getStaff().getId()).orElse(null),
                newBooking.getDateService());

        for (Booking existingBooking : existingBookings) {
            if (existingBooking.getStartTime().equals(newBooking.getStartTime())) {
                return false; // Time slot is already booked
            }
        }

        return true; // Time slot is available
    }
}
