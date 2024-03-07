package com.restaurant.ecommerce.services;

import com.restaurant.ecommerce.DTOs.GoogleUserDataDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
//----------------------------------------------------------------------------------------------------------------------

@Service
public class OAuthService {

  @Value("${GOOGLE_CLIENT_ID}")
  private String clientId;

  @Value("${GOOGLE_CLIENT_SECRET}")
  private String clientSecret;

  @Value("${OAUTH_ENDPOINT}")
  private String redirectUri;

  @Value("${GOOGLE_TOKEN_ENDPOINT}")
  private String tokenEndpoint;

  @Value("${GOOGLE_USER_DATA_ENDPOINT}")
  private String googleEndpoint;

  @Value("${JWT_SECRET}")
  private String JwtSecret;

  @Autowired
  private RestTemplate restTemplate;

//----------------------------------------------------------------------------------------------------------------------

  public String getAccessToken(String authCode) {
    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("code", authCode);
    requestBody.add("client_id", clientId);
    requestBody.add("client_secret", clientSecret);
    requestBody.add("grant_type", "authorization_code");
    requestBody.add("redirect_uri", redirectUri);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<Map> responseEntity = restTemplate.postForEntity(tokenEndpoint, requestEntity, Map.class);

    if (responseEntity.getStatusCode() == HttpStatus.OK) {
      Map<String, String> response = responseEntity.getBody();
      if (response != null && response.containsKey("access_token")) {
        return response.get("access_token");
      }
    }
    return "Couldn't find access token";
  }
//----------------------------------------------------------------------------------------------------------------------

  public GoogleUserDataDTO getUserDataFromGoogle(String accessToken) {
    String userDataUrl = googleEndpoint;
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    HttpEntity<String> entity = new HttpEntity<>("", headers);
    ResponseEntity<String> response = restTemplate.exchange(userDataUrl, HttpMethod.GET, entity, String.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      JSONObject userData = new JSONObject(response.getBody());
      GoogleUserDataDTO gData = new GoogleUserDataDTO();
      gData.setGivenName(userData.getString("given_name"));
      gData.setFamilyName(userData.getString("family_name"));
      gData.setEmail(userData.getString("email"));
      gData.setProfilePic(userData.getString("picture"));
      return gData;
    } else {
      return null;
    }
  }
//----------------------------------------------------------------------------------------------------------------------
  public String createJWT(GoogleUserDataDTO userData) {
    String jwt = Jwts.builder()
            .claim("name", userData.getGivenName())
            .claim("lastname", userData.getFamilyName())
            .claim("email", userData.getEmail())
            .claim("profileImage", userData.getProfilePic())
            .signWith(SignatureAlgorithm.HS256, JwtSecret)
            .compact();
    return jwt;
  }
}
