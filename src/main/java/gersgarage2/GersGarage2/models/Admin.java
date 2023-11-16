package gersgarage2.GersGarage2.models;

import gersgarage2.GersGarage2.enumerates.Gender;
import gersgarage2.GersGarage2.enumerates.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Admin")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber, Gender gender, String dob, Role role, String img) {
        super(firstName, lastName, email, password, confirmPassword, phoneNumber, gender, dob, role, img);
    }
}
