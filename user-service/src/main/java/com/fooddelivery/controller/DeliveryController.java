package com.fooddelivery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.dto.CreateAgentDTO;
import com.fooddelivery.dto.DeliveryAgentDTO;
import com.fooddelivery.dto.UpdateAvailabilityDTO;
import com.fooddelivery.service.DeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    
    @PostMapping("/agents")
    public ResponseEntity<Void> createAgent(@RequestBody CreateAgentDTO agentDTO) {
        deliveryService.createAgent(agentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/agents")
    public List<DeliveryAgentDTO> getAllAgents() {
        return deliveryService.getAllAgents();
    }

    @PatchMapping("/agents/{id}/availability")
    public DeliveryAgentDTO updateAvailability(@PathVariable Long id, @RequestBody UpdateAvailabilityDTO availabilityDTO) {
        return deliveryService.updateAgentAvailability(id, availabilityDTO.isAvailable());
    }
}