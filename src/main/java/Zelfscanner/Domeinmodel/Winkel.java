package Zelfscanner.Domeinmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Winkel implements Serializable {
    private List<Product> allProduct = new ArrayList<Product>();

    private static Winkel my_winkel = new Winkel();
    public static Winkel getWinkel() {
        return my_winkel;
    }

    public static void setWinkel(Winkel winkel) {
        my_winkel = winkel;
    }

    private Winkel() {
        allProduct.add(new Product("Appel", 1.00, "Rode appel"));
        allProduct.add(new Product("Peer", 0.50, "Zoete peer"));
        allProduct.add(new Product("Banaan", 2.00, "Rijpe banaan"));
        allProduct.add(new Product("Kiwi", 0.10, "Goedkope kiwi"));
        allProduct.add(new Product("Ananas", 5.00, "Buitenlandse ananas"));
    }

    public List<Product> getAllProduct() {
        return allProduct;
    }

    public Product getProductByName() {
//        maak hier een getproductbyname
        return null;
    }
}
