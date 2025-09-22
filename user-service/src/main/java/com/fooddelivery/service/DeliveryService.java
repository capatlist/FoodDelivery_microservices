package com.fooddelivery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fooddelivery.dto.CreateAgentDTO;
import com.fooddelivery.dto.DeliveryAgentDTO;
import com.fooddelivery.model.DeliveryAgent;
import com.fooddelivery.repository.DeliveryAgentRepository;

@Service
public class DeliveryService {

    private final DeliveryAgentRepository agentRepository;

    public DeliveryService(DeliveryAgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void createAgent(CreateAgentDTO agentDTO) {
        DeliveryAgent agent = new DeliveryAgent();
        agent.setName(agentDTO.getName());
        agent.setAvailable(true); // Default to available
        agentRepository.save(agent);
    }

    public List<DeliveryAgentDTO> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DeliveryAgentDTO updateAgentAvailability(Long agentId, boolean isAvailable) {
        // 1. Find the agent from the database
        DeliveryAgent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));

        // 2. Set the new availability status
        agent.setAvailable(isAvailable);

        // 3. Save the updated agent back to the database
        agentRepository.update(agent);

        // 4. Return a DTO representing the updated state
        return mapToDTO(agent);
    }

    private DeliveryAgentDTO mapToDTO(DeliveryAgent agent) {
        DeliveryAgentDTO dto = new DeliveryAgentDTO();
        dto.setId(agent.getId());
        dto.setName(agent.getName());
        dto.setAvailable(agent.isAvailable());
        return dto;
    }
}