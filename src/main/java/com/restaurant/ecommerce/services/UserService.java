package com.restaurant.ecommerce.services;

import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


  private final UserRepository userRepository;

  @Autowired
  public UserService (UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public Optional<User> createUser(RegistrationDTO registrationDTO) {
    try {
      User user = registrationDTO.toUserEntity();
      User createdUser = this.userRepository.save(user);
      return Optional.of(createdUser);
    } catch (DataAccessException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
}
