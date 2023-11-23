package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.VehicleDto;
import gersgarage2.GersGarage2.models.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    Vehicle save(VehicleDto vehicleDto);
}
