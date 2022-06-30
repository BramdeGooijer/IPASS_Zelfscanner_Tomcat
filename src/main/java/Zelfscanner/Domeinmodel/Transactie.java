package Zelfscanner.Domeinmodel;

import java.io.Serializable;

public class Transactie implements Serializable {
    private int totaalAantal;
    private double totaalPrijs;

    public Transactie(int totaalAantal, double totaalPrijs) {
        this.totaalAantal = totaalAantal;
        this.totaalPrijs = totaalPrijs;
    }

    public int getTotaalAantal() {
        return totaalAantal;
    }

    public double getTotaalPrijs() {
        return totaalPrijs;
    }
}
