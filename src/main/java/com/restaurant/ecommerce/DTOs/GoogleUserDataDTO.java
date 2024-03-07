package com.restaurant.ecommerce.DTOs;

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
public class GoogleUserDataDTO {
  @NotNull
  @NotBlank
  private String givenName;

  @NotNull
  @NotBlank
  private String familyName;

  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String profilePic;

}
