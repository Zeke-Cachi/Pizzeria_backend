package com.restaurant.ecommerce.serviceTests;

import com.restaurant.ecommerce.DTOs.LoginDTO;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.restaurant.ecommerce.enums.UserRole.USER;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserService mockedUserService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private UserController userController;
  @InjectMocks
  private UserService userService;

//--------------------------------------------------------------------------------------------------
  @Test
  void testIfUserIsCreated() {
    RegistrationDTO registrationDTO = new RegistrationDTO("name", "lastname", "test@test.com", "password", 111111111L, "address", "city");
    User expectedUser = registrationDTO.toUserEntity();
    when(mockedUserService.createUser(any(RegistrationDTO.class)))
            .thenReturn(Optional.of(expectedUser));

    ResponseEntity<User> response = userController.createUser(registrationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(expectedUser);
  }
//--------------------------------------------------------------------------------------------------
  @Test
  void testIfUserIsNotCreated() {
    RegistrationDTO registrationDTO = new RegistrationDTO();
    when(mockedUserService.createUser(any(RegistrationDTO.class)))
            .thenReturn(Optional.empty());

    ResponseEntity<User> response = userController.createUser(registrationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).isNull();
  }
//--------------------------------------------------------------------------------------------------

  @Test
  public void testIfUserLogsIn() {
    LoginDTO loginDTO = new LoginDTO("test@test.com", "password");

    User testUser = new User(1L, "testname", "testlastname", "test@test.com", "password", USER, 1111111111L, "testAdress", "testCity");
    when(this.userRepository.findByEmail(loginDTO.getEmail()))
            .thenReturn(Optional.of(testUser));
    when(this.passwordEncoder.matches(loginDTO.getPassword(), testUser.getPassword())).thenReturn(true);

    Optional<User> result = this.userService.loginUser(loginDTO);

    assertThat(result).isPresent();
  }
}
