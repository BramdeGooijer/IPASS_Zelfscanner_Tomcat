package Zelfscanner.Domeinmodel;

import java.io.Serializable;

public class Product implements Serializable {
    private double prijs;
    private String naam;
    private String beschrijving;
    private String barcode;

    public Product(String naam, double prijs, String beschrijving) {
        this.naam = naam;
        this.prijs = prijs;
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getBarcode() {
        return barcode;
    }
}
