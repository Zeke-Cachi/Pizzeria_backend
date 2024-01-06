package com.restaurant.ecommerce.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pizza_img")
public class PizzaImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pizzaImgId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pizza_id", referencedColumnName = "pizzaId")
  private Pizza pizza;

  @Column(name = "img_url")
  private String imgUrl;
}
