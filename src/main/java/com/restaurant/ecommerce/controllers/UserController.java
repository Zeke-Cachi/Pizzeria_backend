package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.DTOs.LoginDTO;
import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.DTOs.UserDataDTO;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

//------------------------------------------------------------------------------------------------
  @PostMapping("/register")
  public ResponseEntity<String> createUser(@RequestBody RegistrationDTO registrationDTO) {
    String jwt = this.userService.createUser(registrationDTO);
    if (!jwt.equals("couldn't save new user")) {
      return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
//------------------------------------------------------------------------------------------------
  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
    String jwt = this.userService.loginUser(loginDTO);
    if (!jwt.equals("No user found")) {
      return ResponseEntity.status(HttpStatus.OK).body(jwt);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jwt);
    }
  }
}

