package gersgarage2.GersGarage2.repositories;

import gersgarage2.GersGarage2.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
