package com.fooddelivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fooddelivery.model.DeliveryAgent;

@Repository
public class DeliveryAgentRepository {

    private final JdbcTemplate jdbcTemplate;

    public DeliveryAgentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<DeliveryAgent> agentRowMapper = (rs, rowNum) -> {
        DeliveryAgent agent = new DeliveryAgent();
        agent.setId(rs.getLong("id"));
        agent.setName(rs.getString("name"));
        agent.setAvailable(rs.getBoolean("is_available"));
        return agent;
    };

    public List<DeliveryAgent> findAll() {
        String sql = "SELECT * FROM delivery_agents";
        return jdbcTemplate.query(sql, agentRowMapper);
    }

    public Optional<DeliveryAgent> findById(Long id) {
        String sql = "SELECT * FROM delivery_agents WHERE id = ?";
        try {
            DeliveryAgent agent = jdbcTemplate.queryForObject(sql, new Object[]{id}, agentRowMapper);
            return Optional.of(agent);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(DeliveryAgent agent) {
        String sql = "INSERT INTO delivery_agents (name, is_available) VALUES (?, ?)";
        return jdbcTemplate.update(sql, agent.getName(), agent.isAvailable());
    }
    
    public int update(DeliveryAgent agent) {
        String sql = "UPDATE delivery_agents SET name = ?, is_available = ? WHERE id = ?";
        return jdbcTemplate.update(sql, agent.getName(), agent.isAvailable(), agent.getId());
    }
}