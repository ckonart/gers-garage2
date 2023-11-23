package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.BookingDto;
import gersgarage2.GersGarage2.models.Booking;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking save(BookingDto bookingDto);
}
