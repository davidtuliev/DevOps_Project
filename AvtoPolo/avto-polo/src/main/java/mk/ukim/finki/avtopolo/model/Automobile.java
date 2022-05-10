package mk.ukim.finki.avtopolo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Automobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String sellingLocation;

    private String imageUrl;

    @ManyToOne
    private Type type;

    @ManyToOne
    private Brand brand;

    public Automobile() {
    }

    public Automobile(String name, Double price, String sellingLocation,
                      String imageUrl, Type type, Brand brand) {
        this.name = name;
        this.price = price;
        this.sellingLocation = sellingLocation;
        this.imageUrl = imageUrl;
        this.type = type;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSellingLocation() {
        return sellingLocation;
    }

    public void setSellingLocation(String sellingLocation) {
        this.sellingLocation = sellingLocation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
