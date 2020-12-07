package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPSpeli {

    private static final Scanner scanner = new Scanner(System.in);
    private Tekoaly tekoaly;

    public KPSTekoaly() {
        this.tekoaly = new Tekoaly();
    }

    @Override
    protected String pyydaToisenPelaajanSiirtoa(Scanner scanner, String ekanSiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }
}