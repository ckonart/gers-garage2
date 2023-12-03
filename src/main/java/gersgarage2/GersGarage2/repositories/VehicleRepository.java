package gersgarage2.GersGarage2.repositories;

import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByClient(Client client);
}
