package com.restaurant.ecommerce.services;

import com.restaurant.ecommerce.DTOs.LoginDTO;
import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("${JWT_SECRET}")
  private String JwtSecret;

  //-------------------------------------------------------------------------------------------------------
  public String createUser(RegistrationDTO registrationDTO) {
    try {
      User user = registrationDTO.toUserEntity();
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      User createdUser = this.userRepository.save(user);
      return Jwts.builder()
              .claim("givenName", createdUser.getName())
              .claim("familyName", createdUser.getLastname())
              .claim("email", createdUser.getEmail())
              .claim("profileImage", createdUser.getProfilePic())
              .signWith(SignatureAlgorithm.HS256, JwtSecret)
              .compact();
    } catch (DataAccessException e) {
      e.printStackTrace();
      return "couldn't save new user";
    }
  }
//-------------------------------------------------------------------------------------------------------
  public String loginUser(LoginDTO loginDTO) {
    Optional<User> doesUserExists = this.userRepository.findByEmail(loginDTO.getEmail());
    if (doesUserExists.isPresent()) {
      if (passwordEncoder.matches(loginDTO.getPassword(), doesUserExists.get().getPassword())) {
        return Jwts.builder()
                .claim("givenName", doesUserExists.get().getName())
                .claim("familyName", doesUserExists.get().getLastname())
                .claim("email", doesUserExists.get().getEmail())
                .claim("profileImage", doesUserExists.get().getProfilePic())
                .signWith(SignatureAlgorithm.HS256, JwtSecret)
                .compact();
      }
    }
    return "No user found";
  }

}
