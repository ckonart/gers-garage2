package gersgarage2.GersGarage2.repositories;

import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value="select * from booking", nativeQuery = true)
    public List<Booking> findAllBookings();

    List<Booking> findByClient(Client client);
}
