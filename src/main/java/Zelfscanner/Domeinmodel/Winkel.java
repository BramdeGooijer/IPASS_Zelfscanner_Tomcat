package Zelfscanner.Domeinmodel;

import javassist.NotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Winkel implements Serializable {
    private List<Product> allProduct = new ArrayList<Product>();
    private List<Medewerker> allMedewerkers = new ArrayList<Medewerker>();
    private List<Transactie> allTransacties = new ArrayList<Transactie>();

    private static Winkel my_winkel = new Winkel();
    public static Winkel getWinkel() {
        return my_winkel;
    }

    public static void setWinkel(Winkel winkel) {
        my_winkel = winkel;
    }

    private Winkel() {
        allProduct.add(new Product("Appel", 1.00, "Rode appel", "IPASS-Appel"));
        allProduct.add(new Product("Peer", 0.50, "Zoete peer", "IPASS-Peer"));
        allProduct.add(new Product("Banaan", 2.00, "Rijpe banaan", "IPASS-Banaan"));
        allProduct.add(new Product("Kiwi", 0.10, "Goedkope kiwi", "IPASS-Kiwi"));
        allProduct.add(new Product("Ananas", 5.00, "Buitenlandse ananas", "IPASS-Ananas"));
        allMedewerkers.add(new Medewerker("Bram de Gooijer", "Bram", "bram"));
        allMedewerkers.add(new Medewerker("Admin", "Admin", "admin"));
    }

    public List<Product> getAllProduct() {
        return allProduct;
    }

    public List<Medewerker> getAllMedewerkers() {
        return allMedewerkers;
    }

    public List<Transactie> getAllTransacties() {
        return allTransacties;
    }

    public Product getProductByBarcode(String barcode) throws NotFoundException {
        for (Product perProduct : allProduct) {
            if (perProduct.getBarcode().equals(barcode)) {
                return perProduct;
            }
        }
        throw new NotFoundException("Product bestaat niet!");
    }

    public Medewerker getMedewerkerByName(String naam) throws NotFoundException {
        for (Medewerker perMedewerker : allMedewerkers) {
            if (perMedewerker.getNaam().equals(naam)) {
                return perMedewerker;
            }
        }
        throw new NotFoundException("Medewerker bestaat niet!");
    }

    public void addTransactie(int totaalAantal, double totaalPrijs) {
        allTransacties.add(new Transactie(totaalAantal, totaalPrijs));
    }
}
