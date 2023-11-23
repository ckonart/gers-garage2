package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Staff;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ClientService {

    Client save(ClientDto clientDto);

    Client updatePassword(Integer id, String password);
    Client updateConfirmPassword(Integer id, String confirmPassword);

    List<Client> getAllClient();
}
