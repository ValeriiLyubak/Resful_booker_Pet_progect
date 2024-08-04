package models;

import lombok.NoArgsConstructor;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthentificationRequest {
    private String username;
    private String password;
}
