package gersgarage2.GersGarage2.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product_record")
public class ProductRecord {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "qtd_sale")
    private Integer qtdSale;

    @ManyToOne
    @JoinColumn(name = "id_client_fk")
    private Client client;

    @OneToMany(mappedBy = "productRecord")
    private List<Product> productList;

    public ProductRecord() {
    }

    public ProductRecord(Long id, Date saleDate, Integer qtdSale) {
        this.id = id;
        this.saleDate = saleDate;
        this.qtdSale = qtdSale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getQtdSale() {
        return qtdSale;
    }

    public void setQtdSale(Integer qtdSale) {
        this.qtdSale = qtdSale;
    }
}
