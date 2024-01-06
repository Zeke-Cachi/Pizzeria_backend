package com.restaurant.ecommerce.services;

import com.restaurant.ecommerce.models.Pizza;
import com.restaurant.ecommerce.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
  private final PizzaRepository pizzaRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  public List<Pizza> getAllPizzas() {
    return this.pizzaRepository.findAll();
  }
}
