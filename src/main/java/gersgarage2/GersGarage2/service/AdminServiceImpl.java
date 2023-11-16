package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Admin;
import gersgarage2.GersGarage2.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin save(ClientDto clientDto) {
        Admin admin = new Admin(clientDto.getFirstName(), clientDto.getLastName(), clientDto.getEmail(), passwordEncoder.encode(clientDto.getPassword()), passwordEncoder.encode(clientDto.getConfirmPassword()), clientDto.getPhoneNumber(), clientDto.getGender(), clientDto.getDob(), clientDto.getRole(), clientDto.getImg());
        return adminRepository.save(admin);
    }
}
