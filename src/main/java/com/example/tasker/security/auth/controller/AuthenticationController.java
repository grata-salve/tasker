package com.example.tasker.security.auth.controller;

import com.example.tasker.security.auth.model.AuthenticationRequest;
import com.example.tasker.security.auth.model.AuthenticationResponse;
import com.example.tasker.security.auth.model.RegisterRequest;
import com.example.tasker.security.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponse register(@RequestBody @Valid RegisterRequest request) {
    return service.register(request);
  }

  @PostMapping("/authenticate")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponse authenticate(@RequestBody @Valid AuthenticationRequest request) {
    return service.authenticate(request);
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    service.refreshToken(request, response);
  }
}
