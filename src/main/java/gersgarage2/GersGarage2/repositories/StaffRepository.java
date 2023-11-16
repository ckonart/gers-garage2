package gersgarage2.GersGarage2.repositories;

import gersgarage2.GersGarage2.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff findByEmail(String email);

    List<Staff> findByRole(String role);

    /*List<Staff> findByName(String firstName);*/
}
