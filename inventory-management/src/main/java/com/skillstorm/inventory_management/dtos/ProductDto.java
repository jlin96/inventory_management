package com.skillstorm.inventory_management.dtos;

public class ProductDto {
    
    private int id;
    private String name;
    private String description;
    private int stockAmount;
    private int warehouseId;

    public ProductDto() {}

    public ProductDto(int id, String name, String description, int stockAmount, int warehouseId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stockAmount = stockAmount;
        this.warehouseId = warehouseId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    
    
}
