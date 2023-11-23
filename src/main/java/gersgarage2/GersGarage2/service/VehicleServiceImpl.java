package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.VehicleDto;
import gersgarage2.GersGarage2.models.Vehicle;
import gersgarage2.GersGarage2.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(VehicleDto vehicleDto) {
        Vehicle vehicle = convertToVehicle(vehicleDto);
        return vehicleRepository.save(vehicle);
    }

    private Vehicle convertToVehicle(VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.getType(),
                vehicleDto.getBrand(),
                vehicleDto.getModel(),
                vehicleDto.getColor(),
                vehicleDto.getYear(),
                vehicleDto.getEngineSize(),
                vehicleDto.getFuel(),
                vehicleDto.getPlate(),
                vehicleDto.getMileage(),
                vehicleDto.getDetails(),
                vehicleDto.getClient(),
                vehicleDto.getBookingList()
        );
    }
    }

