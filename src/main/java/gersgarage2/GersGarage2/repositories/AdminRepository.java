package gersgarage2.GersGarage2.repositories;

import gersgarage2.GersGarage2.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByEmail(String email);
}
