package com.restaurant.ecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cart")
@Getter
@Setter
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "pizza_id", referencedColumnName = "pizzaId")
  private Pizza pizza;

  @Column
  @NotNull
  private int quantity = 0;

}
