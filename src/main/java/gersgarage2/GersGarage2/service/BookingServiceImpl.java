package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.BookingDto;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
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
}
