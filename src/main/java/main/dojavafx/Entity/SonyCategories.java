package main.dojavafx.Entity;


//import jakarta.persistence.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SonyCategories")
public class SonyCategories {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "CateID")
    private int cateID;

    @Column(name = "CateName", length = 50)
    private String cateName;

    @Column(name = "Status", length = 10)
    private String status;

    @OneToMany(mappedBy = "category")
    private List<SonyProducts> productsList = new ArrayList<>();

    public SonyCategories(String cateName, String status) {
        this.cateName = cateName;
        this.status = status;
    }

    public SonyCategories() {}



    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
