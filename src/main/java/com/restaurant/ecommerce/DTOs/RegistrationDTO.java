package com.restaurant.ecommerce.DTOs;

import com.restaurant.ecommerce.enums.UserRole;
import com.restaurant.ecommerce.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

  @NotNull(message = "Field 'name' cannot be null")
  private String name;

  @NotNull(message = "Field 'lastname' cannot be null")
  private String lastname;

  @NotNull(message = "Field 'email' cannot be null")
  @Email
  private String email;

  private String password;

  private Long phoneNumber;

  private String address;

  private String city;

  public User toUserEntity() {
    User user = new User();
    user.setName(this.name);
    user.setLastname(this.lastname);
    user.setEmail(this.email);
    user.setPassword(this.password);
    user.setPhoneNumber(this.phoneNumber);
    user.setAddress(this.address);
    user.setCity(this.city);
    user.setUserRole(UserRole.USER);
    return user;
  }
}