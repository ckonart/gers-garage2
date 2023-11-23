package gersgarage2.GersGarage2.dto;

import gersgarage2.GersGarage2.enumerates.*;
import gersgarage2.GersGarage2.models.Booking;
import gersgarage2.GersGarage2.models.Client;

import java.util.List;

public class VehicleDto {

    private String model;
    private CarMaker brand;
    private Color color;
    private Integer year;
    private TypeVehicle type;
    private EngineSize engineSize;
    private Fuel fuel;
    private String plate;
    private Integer mileage;
    private String details;
    private Client client;
    private List<Booking> bookingList;

    public VehicleDto(String model, CarMaker brand, Color color, Integer year, TypeVehicle type, EngineSize engineSize, Fuel fuel, String plate, Integer mileage, String details, Client client, List<Booking> bookingList) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
