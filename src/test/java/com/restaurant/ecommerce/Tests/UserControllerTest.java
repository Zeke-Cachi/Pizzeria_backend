package com.restaurant.ecommerce.Tests;

import com.restaurant.ecommerce.DTOs.RegistrationDTO;
import com.restaurant.ecommerce.DTOs.UserDataDTO;
import com.restaurant.ecommerce.controllers.UserController;
import com.restaurant.ecommerce.models.User;
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
public class UserControllerTest {
  @Mock
  private UserService mockedUserService;
  @InjectMocks
  private UserController userController;

  //--------------------------------------------------------------------------------------------------
  @Test
  void testIfUserIsCreated() {
    RegistrationDTO registrationDTO = new RegistrationDTO(
            "name",
            "lastname",
            "test@test.com",
            "password",
            111111111L,
            "address",
            "city");
    User expectedUser = registrationDTO.toUserEntity();
    UserDataDTO userData = new UserDataDTO(
            expectedUser.getName(),
            expectedUser.getLastname(),
            expectedUser.getEmail(),
            expectedUser.getProfilePic());
    when(mockedUserService.createUser(any(RegistrationDTO.class)))
            .thenReturn(Optional.of(expectedUser));

    ResponseEntity<UserDataDTO> response = userController.createUser(registrationDTO);
    System.out.println(response.getBody());
    System.out.println(userData);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(userData);
  }
  //--------------------------------------------------------------------------------------------------
  @Test
  void testIfUserIsNotCreated() {
    RegistrationDTO registrationDTO = new RegistrationDTO();
    when(mockedUserService.createUser(any(RegistrationDTO.class)))
            .thenReturn(Optional.empty());

    ResponseEntity<UserDataDTO> response = userController.createUser(registrationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).isNull();
  }
}
