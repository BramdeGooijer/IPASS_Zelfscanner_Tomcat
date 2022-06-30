package Zelfscanner.Domeinmodel;

import java.io.Serializable;

public class Product implements Serializable {
    private double prijs;
    private String naam;
    private String beschrijving;
    private String barcode;

    public Product(String naam, double prijs, String beschrijving, String barcode) {
        this.naam = naam;
        this.prijs = prijs;
        this.beschrijving = beschrijving;
        this.barcode = barcode;
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

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
