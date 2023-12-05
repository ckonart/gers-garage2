package gersgarage2.GersGarage2.models;

import gersgarage2.GersGarage2.enumerates.Gender;
import gersgarage2.GersGarage2.enumerates.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client extends User{

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Vehicle> VehicleList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ProductRecord> productRecordList;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber, Gender gender, String dob, Role role, String img) {
        super(firstName, lastName, email, password, confirmPassword, phoneNumber, gender, dob, role, img);
    }
}

