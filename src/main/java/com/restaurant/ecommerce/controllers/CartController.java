package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.models.Cart;
import com.restaurant.ecommerce.services.CartService;
import com.restaurant.ecommerce.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

  private final CartService cartService;

  @Autowired
  private CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping("/store-cart-data")
  public void storeCartData(@RequestBody Cart cart) {

  }
}
