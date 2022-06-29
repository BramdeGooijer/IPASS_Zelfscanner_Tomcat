package Zelfscanner.Domeinmodel;

import java.io.Serializable;

public class Medewerker implements Serializable {
    private String naam;
    private String username;
    private String password;

    public Medewerker(String naam, String username, String password) {
        this.naam = naam;
        this.username = username;
        this.password = password;
    }

    public String getNaam() {
        return naam;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
