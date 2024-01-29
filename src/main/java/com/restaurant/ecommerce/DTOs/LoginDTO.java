package com.restaurant.ecommerce.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
  @NotNull
  @NotBlank
  private String email;

  @NotNull
  private String password;
}
