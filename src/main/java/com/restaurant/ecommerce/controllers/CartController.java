package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.models.Cart;
import com.restaurant.ecommerce.services.CartService;
import com.restaurant.ecommerce.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<String> storeCartData(@RequestBody Cart cart) {
    Boolean response = this.cartService.storeCartData(cart);
    return ResponseEntity.ok(response ? "success!" : "failure");
  }
}
