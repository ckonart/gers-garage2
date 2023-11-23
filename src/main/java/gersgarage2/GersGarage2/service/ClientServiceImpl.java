package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Staff;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
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
        Client client = convertToClient(clientDto);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> client = clientRepository.findAll();
        return client;
    }

    public Client updatePassword(Integer id, String password) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        client.setPassword(passwordEncoder.encode(password));
        return clientRepository.save(client);
    }

    public Client updateConfirmPassword(Integer id, String confirmPassword) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        client.setConfirmPassword(passwordEncoder.encode(confirmPassword));
        return clientRepository.save(client);
    }

    private Client convertToClient(ClientDto clientDto) {
        return new Client(
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                passwordEncoder.encode(clientDto.getPassword()),
                passwordEncoder.encode(clientDto.getConfirmPassword()),
                clientDto.getPhoneNumber(),
                clientDto.getGender(),
                clientDto.getDob(),
                clientDto.getRole(),
                clientDto.getImg()
        );
    }
}
