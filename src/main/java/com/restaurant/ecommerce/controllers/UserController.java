package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  private UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> createUser(@RequestBody RegistrationDTO registrationDTO) {
    Optional<User> newUser = this.userService.createUser(registrationDTO);
    if (newUser.isPresent()) {
      User createdUser = newUser.get();
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
}

