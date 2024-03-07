package com.restaurant.ecommerce.controllers;

import com.restaurant.ecommerce.DTOs.GoogleUserDataDTO;
import com.restaurant.ecommerce.services.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
@RequestMapping("/oauth2/callback/google/")
public class OauthController {

  @Autowired
  private OAuthService oAuthService;

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping
  public void getUserData(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
    String accessToken = oAuthService.getAccessToken(code);
    if (!accessToken.equals("Couldn't find access token")) {
      GoogleUserDataDTO userData = oAuthService.getUserDataFromGoogle(accessToken);
      String jwt = oAuthService.createJWT(userData);
      response.sendRedirect("http://localhost:5173?token=" + jwt);
    }
  }
}
