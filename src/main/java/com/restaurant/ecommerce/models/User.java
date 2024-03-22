package com.restaurant.ecommerce.models;


import com.restaurant.ecommerce.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column
  @NotNull(message="Field 'name' cannot be null")
  private String name;

  @Column
  @NotNull(message="Field 'lastname' cannot be null")
  private String lastname;

  @Column
  @NotNull(message="Field 'email' cannot be null")
  @Email
  private String email;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "role")
  @NotNull
  private UserRole userRole;

  @Column(name = "phone_number")
  private Long phoneNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "profile_img")
  private String profilePic;
}
