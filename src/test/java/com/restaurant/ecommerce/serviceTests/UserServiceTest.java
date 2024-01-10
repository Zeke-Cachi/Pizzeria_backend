package com.restaurant.ecommerce.serviceTests;

import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.controllers.UserController;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.repositories.UserRepository;
import com.restaurant.ecommerce.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserService userServiceTest;
  @Mock
  private UserRepository userRepositoryTest;
  @InjectMocks
  private UserController userController;

  @Test
  void testIfUserIsCreated() {
    RegistrationDTO registrationDTO = new RegistrationDTO();
    User expectedUser = registrationDTO.toUserEntity();
    when(userServiceTest.createUser(any(RegistrationDTO.class)))
            .thenReturn(Optional.of(expectedUser));

    ResponseEntity<User> response = userController.createUser(registrationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(expectedUser);
  }

  @Test
  void testIfUserIsNotCreated() {
    RegistrationDTO registrationDTO = new RegistrationDTO();
    User expectedUser = registrationDTO.toUserEntity();
    when(userServiceTest.createUser(any(RegistrationDTO.class)))
            .thenReturn(Optional.empty());

    ResponseEntity<User> response = userController.createUser(registrationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).isNull();
  }
}
