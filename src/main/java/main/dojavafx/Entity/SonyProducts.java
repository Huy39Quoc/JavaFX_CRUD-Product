package main.dojavafx.Entity;

//import jakarta.persistence.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "SonyProducts")
public class SonyProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private long productID;

    @Column(name = "ProductName", length = 50)
    private String productName;

    @Column(name = "Price")
    private int price;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "CateID")
    private SonyCategories category;

    public SonyProducts(String productName, int price, int stock, LocalDateTime createdAt, SonyCategories category) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.category = category;
    }

    public SonyProducts() {}

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public SonyCategories getCategory() {
        return category;
    }

    public void setCategory(SonyCategories category) {
        this.category = category;
    }
}
