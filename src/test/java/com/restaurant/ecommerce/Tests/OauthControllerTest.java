package com.restaurant.ecommerce.Tests;

import com.restaurant.ecommerce.DTOs.GoogleUserDataDTO;
import com.restaurant.ecommerce.controllers.OAuthController;
import com.restaurant.ecommerce.services.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//----------------------------------------------------------------------------------------------------------------------

@ExtendWith(MockitoExtension.class)
public class OauthControllerTest {

  @Mock
  private OAuthService oAuthService;

  @InjectMocks
  private OAuthController oauthController;

  @Value("${TEST_AUTH_CODE}")
  private String testAuthCode;

  @Value("${TEST_ACCESS_TOKEN}")
  private String testAccessToken;

  @Value("${TEST_JWT}")
  private String testJWTString;

//----------------------------------------------------------------------------------------------------------------------

  @Test
  void testIfGetUserDataGivesCorrectResponse() throws IOException {
    //Given
    String code = testAuthCode;
    String authToken = testAccessToken;
    String jwtTest = testJWTString;
    HttpServletResponse response = mock(HttpServletResponse.class);
    GoogleUserDataDTO testUser = new GoogleUserDataDTO("testname", "testLastname", "test@test.com", "www.profilepicurl.com");

    //When
    when(oAuthService.getAccessToken(code))
            .thenReturn(authToken);
    when(oAuthService.getUserDataFromGoogle(authToken))
            .thenReturn(testUser);
    when(oAuthService.createJWT(testUser))
            .thenReturn(jwtTest);


    String accessTokenTest = oAuthService.getAccessToken(code);
    GoogleUserDataDTO testResponse = oAuthService.getUserDataFromGoogle(accessTokenTest);
    String testJwt = oAuthService.createJWT(testResponse);


    //Then
    assertThat(accessTokenTest).isEqualTo(authToken);
    assertThat(testResponse).isEqualTo(testUser);
    assertThat(testJwt).isEqualTo(jwtTest);
  }
}
