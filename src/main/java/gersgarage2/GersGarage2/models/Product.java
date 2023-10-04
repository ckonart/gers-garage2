package gersgarage2.GersGarage2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private double cost;
    @Column(name = "quantity_stock")
    private Integer quantityStock;

    @ManyToOne
    @JoinColumn(name = "id_product_fk")
    private ProductRecord productRecord;

    public Product() {
    }

    public Product(Integer id, String name, double cost, Integer quantityStock) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantityStock = quantityStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }
}
