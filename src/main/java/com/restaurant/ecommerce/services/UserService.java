package com.restaurant.ecommerce.services;

import com.restaurant.ecommerce.DTOs.LoginDTO;
import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Autowired
  public UserService (UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public Optional<User> createUser(RegistrationDTO registrationDTO) {
    try {
      User user = registrationDTO.toUserEntity();
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      User createdUser = this.userRepository.save(user);
      return Optional.of(createdUser);
    } catch (DataAccessException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
//-------------------------------------------------------------------------------------------------------
  public Optional<User> loginUser(LoginDTO loginDTO) {
    Optional<User> doesUserExists = this.userRepository.findByEmail(loginDTO.getEmail());
    if (doesUserExists.isPresent()) {
      if (passwordEncoder.matches(loginDTO.getPassword(), doesUserExists.get().getPassword())) {
        return doesUserExists;
      }
    }
    return Optional.empty();
  }
}
