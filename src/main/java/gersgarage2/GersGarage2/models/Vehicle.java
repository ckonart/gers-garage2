package gersgarage2.GersGarage2.models;

import gersgarage2.GersGarage2.enumerates.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private CarMaker brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeVehicle type;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_size")
    private EngineSize engineSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    private Fuel fuel;

    @Column(name = "plate")
    private String plate;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "id_client_fk")
    private Client client;

    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookingList;

    public Vehicle() {
    }

    public Vehicle(Integer id, String model, CarMaker brand, Color color, Integer year, TypeVehicle type, EngineSize engineSize, Fuel fuel, String plate, Integer mileage, String details, Client client, List<Booking> bookingList) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.year = year;
        this.type = type;
        this.engineSize = engineSize;
        this.fuel = fuel;
        this.plate = plate;
        this.mileage = mileage;
        this.details = details;
        this.client = client;
        this.bookingList = bookingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarMaker getBrand() {
        return brand;
    }

    public void setBrand(CarMaker brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public TypeVehicle getType() {
        return type;
    }

    public void setType(TypeVehicle type) {
        this.type = type;
    }

    public EngineSize getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(EngineSize engineSize) {
        this.engineSize = engineSize;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
