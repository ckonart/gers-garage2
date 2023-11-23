package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {

    Staff save(ClientDto clientDto);

    List<Staff> getAllStaff();

}
