package com.fooddelivery.dto;

public class DeliveryAgentDTO {
    private Long id;
    private String name;
    private boolean isAvailable;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}