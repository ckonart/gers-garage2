package gersgarage2.GersGarage2.service;

import gersgarage2.GersGarage2.models.Admin;
import gersgarage2.GersGarage2.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username);
        if(admin == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(admin);
    }
}
