package gersgarage2.GersGarage2.models;

import gersgarage2.GersGarage2.enumerates.BookingStatus;
import gersgarage2.GersGarage2.enumerates.ServiceType;
import jakarta.persistence.*;

import java.util.Date;

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
    private Date dateService;
    @Column(name = "start_time")
    private String dateTime;
    @Column(name = "details")
    private String details;
    @Column(name = "cost_Service")
    private double costService;

    /*@Column(name = "extra_cost")
    private double extraCost;
    @Column(name = "total_cost")
    private double totalCost;*/

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

    public Booking(Integer id, ServiceType serviceType, BookingStatus status, Date dateService, String dateTime, String details, double costService, Client client, Vehicle vehicle, Staff staff) {
        this.id = id;
        this.serviceType = serviceType;
        this.status = status;
        this.dateService = dateService;
        this.dateTime = dateTime;
        this.details = details;
        this.costService = costService;
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

    public Date getDateService() {
        return dateService;
    }

    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getCostService() {
        return costService;
    }

    public void setCostService(double costService) {
        this.costService = costService;
    }
}
