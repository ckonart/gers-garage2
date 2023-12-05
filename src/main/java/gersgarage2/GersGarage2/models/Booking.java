package gersgarage2.GersGarage2.models;

import gersgarage2.GersGarage2.enumerates.BookingStatus;
import gersgarage2.GersGarage2.enumerates.ServiceType;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private String dateService;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "details")
    private String details;
    @Column(name = "cost_Service")
    private Double costService;

    @Column(name = "extra_cost")
    private Double extraCost;
    @Column(name = "total_cost")
    private Double totalCost;

    @ManyToOne
    @JoinColumn(name = "id_client_fk")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_vehicle_fk")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "id_staff_fk")
    private Staff staff;

    public Booking() {
    }

    public Booking(Integer id, ServiceType serviceType, BookingStatus status, String dateService, String startTime, String details, Double costService, Double extraCost, Double totalCost, Client client, Vehicle vehicle, Staff staff) {
        this.id = id;
        this.serviceType = serviceType;
        this.status = status;
        this.dateService = dateService;
        this.startTime = startTime;
        this.details = details;
        this.costService = costService;
        this.extraCost = extraCost;
        this.totalCost = totalCost;
        this.client = client;
        this.vehicle = vehicle;
        this.staff = staff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", serviceType=" + serviceType +
                ", client=" + client +
                ", vehicle=" + vehicle +
                '}';
    }
}


