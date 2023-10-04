package gersgarage2.GersGarage2.models;

import gersgarage2.GersGarage2.enumerates.Gender;
import gersgarage2.GersGarage2.enumerates.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "Staff")
public class Staff extends User{



    public Staff() {
    }

    public Staff(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber, Gender gender, LocalDate dob, Role role) {
        super(firstName, lastName, email, password, confirmPassword, phoneNumber, gender, dob, role);
    }


}
