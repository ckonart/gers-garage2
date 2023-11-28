package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.BookingDto;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    Booking save(BookingDto bookingDto);

    boolean isTimeSlotAvailable(BookingDto newBooking);
}
