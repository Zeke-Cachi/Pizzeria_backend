package com.restaurant.ecommerce.repositories;

import com.restaurant.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
