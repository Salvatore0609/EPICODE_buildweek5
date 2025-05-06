package it.epicode.EPICODE_buildweek5.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
