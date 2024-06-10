package com.server.auth.requests;

public class RegisterRequest {
  private String name;
  // private String surname;
  private String email;
  private String password;
  // private Role role;

  public String getEmail() {
    return email;
  }

  public RegisterRequest setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public RegisterRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getName() {
    return name;
  }

  public RegisterRequest setName(String name) {
    this.name = name;
    return this;
  }
}
