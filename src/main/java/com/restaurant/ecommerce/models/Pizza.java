package com.restaurant.ecommerce.models;

import com.restaurant.ecommerce.enums.PizzaBakeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long pizzaId;

  @Column(name = "pizza_name")
  @NotBlank
  @NotNull
  private String pizzaName;

  @Column(name="pizza_description")
  @NotBlank
  @NotNull
  private String pizzaDescription;

  @Column(name="pizza_image")
  @NotBlank
  @NotNull
  private String pizzaImg;

  @Column(name="pizza_topping")
  @NotBlank
  @NotNull
  private String pizzaTopping;


  @Enumerated(EnumType.ORDINAL)
  @Column(name="pizza_bake_type")
  @NotBlank
  @NotNull
  private PizzaBakeType pizzaBakeType;
}
