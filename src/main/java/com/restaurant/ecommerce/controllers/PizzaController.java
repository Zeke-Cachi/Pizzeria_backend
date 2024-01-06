package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.models.Pizza;
import com.restaurant.ecommerce.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/pizzas")
public class PizzaController {

  private final PizzaService pizzaService;

  @Autowired
  private PizzaController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  @GetMapping
  public ResponseEntity<List<Pizza>> getAllPizzas() {
    return new ResponseEntity<>(this.pizzaService.getAllPizzas(), HttpStatus.OK);
  }

}
