package models;

import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigFactory;
import config.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationRequest {
    private String username, password;
}
