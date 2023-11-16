package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Staff;
import gersgarage2.GersGarage2.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Staff save(ClientDto clientDto) {
        Staff staff = new Staff(clientDto.getFirstName(), clientDto.getLastName(), clientDto.getEmail(), passwordEncoder.encode(clientDto.getPassword()), passwordEncoder.encode(clientDto.getConfirmPassword()), clientDto.getPhoneNumber(), clientDto.getGender(), clientDto.getDob(), clientDto.getRole(), clientDto.getImg());
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staff = staffRepository.findAll();
        return staff;
    }

    @Override
    public List<Staff> getStaffByRole(String role) {
        List<Staff> staff = staffRepository.findByRole(role);
        return staff;
    }

    /*@Override
    public List<Staff> getStaffByName(String firstName) {
        List<Staff> staff = staffRepository.findByName(firstName);
        return staff;
    }*/
}
