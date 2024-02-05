package com.restaurant.ecommerce.repositories;

import com.restaurant.ecommerce.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> { }
