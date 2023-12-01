package gersgarage2.GersGarage2.dto;

import gersgarage2.GersGarage2.enumerates.BookingStatus;
import gersgarage2.GersGarage2.enumerates.ServiceType;
import gersgarage2.GersGarage2.models.Client;
import gersgarage2.GersGarage2.models.Staff;
import gersgarage2.GersGarage2.models.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingDto {

    @NotNull
    private ServiceType serviceType;
    @NotNull
    private BookingStatus status;
    @NotBlank
    private String dateService;
    @NotBlank
    private String startTime;
    @NotBlank
    private String details;
    private Double costService;

    private Double extraCost;
    @NotBlank
    private Double totalCost;
    @NotBlank
    private Staff staff;
    @NotBlank
    private Client client;
    @NotBlank
    private Vehicle vehicle;

    public BookingDto(ServiceType serviceType, BookingStatus status, String dateService, String startTime, String details, Double costService, Double extraCost, Double totalCost, Client client, Vehicle vehicle, Staff staff) {
        this.serviceType = serviceType;
        this.status = status;
        this.dateService = dateService;
        this.startTime = startTime;
        this.details = details;
        this.costService = 0.0;
        this.extraCost = 0.0;
        this.totalCost = 0.0;
        this.client = client;
        this.vehicle = vehicle;
        this.staff = staff;
    }


    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getDateService() {
        return dateService;
    }

    public void setDateService(String dateService) {
        this.dateService = dateService;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getCostService() {
        return costService;
    }

    public void setCostService(Double costService) {
        this.costService = costService;
    }

    public Double getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(Double extraCost) {
        this.extraCost = extraCost;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
