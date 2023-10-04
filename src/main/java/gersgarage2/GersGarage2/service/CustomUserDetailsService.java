package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = clientRepository.findByEmail(username);
        if(client == null){
            throw new UsernameNotFoundException("User not found.");
        }
        return new CustomUserDetail(client);
    }
}
