package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(ClientDto clientDto) {
        Client client  = new Client(clientDto.getFirstName(), clientDto.getLastName(), clientDto.getEmail(), passwordEncoder.encode(clientDto.getPassword()), passwordEncoder.encode(clientDto.getConfirmPassword()), clientDto.getPhoneNumber(), clientDto.getGender(), clientDto.getDob(), clientDto.getRole(), clientDto.getImg());
        return clientRepository.save(client);
    }


}
