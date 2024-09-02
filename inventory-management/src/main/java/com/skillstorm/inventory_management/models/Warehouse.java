package com.skillstorm.inventory_management.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String name;

    @Column(length = 50, nullable = false)
    private String address;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Product> product;

    // public Warehouse() {}

    // public Warehouse(int id, String name, String address, List<Product> product) {
    //     this.id = id;
    //     this.name = name;
    //     this.address = address;
    //     this.product = product;
    // }

    // public Warehouse addProduct(Product newProduct) {
    //     product.add(newProduct);
    //     newProduct.setWarehouse(this);
    //     return this;
    // }

    // public Warehouse removeProduct(Product oldProduct) {
    //     product.remove(oldProduct);
    //     oldProduct.setWarehouse(null);
    //     return this;
    // }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", address=" + address + ", product=" + product + "]";
    }

    
}
