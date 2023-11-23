package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.models.User;
import gersgarage2.GersGarage2.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = clientRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found.");
        }
        return new CustomUserDetail(user);
    }

}
