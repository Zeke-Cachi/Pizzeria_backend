package com.restaurant.ecommerce.services;

import com.restaurant.ecommerce.models.Cart;
import com.restaurant.ecommerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;

  private CartService(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }
  public Boolean storeCartData(Cart cart) {
    try {
      Cart response = this.cartRepository.save(cart);
      System.out.println(response);
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
