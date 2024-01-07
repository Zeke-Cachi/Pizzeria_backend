package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    User newUser = this.userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }
}

