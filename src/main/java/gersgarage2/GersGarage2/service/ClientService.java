package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.dto.ClientDto;
import gersgarage2.GersGarage2.models.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    Client save(ClientDto clientDto);


}
