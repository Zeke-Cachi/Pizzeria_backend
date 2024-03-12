package com.restaurant.ecommerce.Tests;

import com.restaurant.ecommerce.DTOs.LoginDTO;
import com.restaurant.ecommerce.models.User;
import com.restaurant.ecommerce.repositories.UserRepository;
import com.restaurant.ecommerce.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.restaurant.ecommerce.enums.UserRole.USER;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private UserService userService;


//--------------------------------------------------------------------------------------------------
  @Test
  public void testIfUserLogsIn() {
    LoginDTO loginDTO = new LoginDTO("test@test.com", "password");

    User testUser = new User(
            1L,
            "testname",
            "testlastname",
            "test@test.com",
            "password",
            USER,
            1111111111L,
            "testAdress",
            "testCity",
            "www.profilepicurl.com");
    when(this.userRepository.findByEmail(loginDTO.getEmail()))
            .thenReturn(Optional.of(testUser));
    when(this.passwordEncoder.matches(loginDTO.getPassword(), testUser.getPassword())).thenReturn(true);

    Optional<User> result = this.userService.loginUser(loginDTO);

    assertThat(result).isPresent();
  }
  //--------------------------------------------------------------------------------------------------
  @Test
  public void testIfUserEmailIsNotFound() {
    LoginDTO loginDTO = new LoginDTO("test@test.com", "password");

    User testUser = new User(
            1L,
            "testname",
            "testlastname",
            "test@test.com",
            "password",
            USER,
            1111111111L,
            "testAdress",
            "testCity",
            "www.profilepicurl.com");
    when(this.userRepository.findByEmail(loginDTO.getEmail()))
            .thenReturn(Optional.empty());

    Optional<User> result = this.userService.loginUser(loginDTO);

    assertThat(result).isEmpty();
  }
  //--------------------------------------------------------------------------------------------------
  @Test
  public void testIfUserPasswordDoesntMatch() {
    LoginDTO loginDTO = new LoginDTO("test@test.com", "password");

    User testUser = new User(
            1L,
            "testname",
            "testlastname",
            "test@test.com",
            "password",
            USER,
            1111111111L,
            "testAdress",
            "testCity",
            "www.profilepicurl.com");
    when(this.userRepository.findByEmail(loginDTO.getEmail()))
            .thenReturn(Optional.of(testUser));
    when(this.passwordEncoder.matches(loginDTO.getPassword(), testUser.getPassword())).thenReturn(false);

    Optional<User> result = this.userService.loginUser(loginDTO);

    assertThat(result).isEmpty();
  }
}
