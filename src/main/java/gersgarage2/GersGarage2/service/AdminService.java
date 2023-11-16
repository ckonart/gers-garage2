package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {



    Admin save(ClientDto clientDto);
}
