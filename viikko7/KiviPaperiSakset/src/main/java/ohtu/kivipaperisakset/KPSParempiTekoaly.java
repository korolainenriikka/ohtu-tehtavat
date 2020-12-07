package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSpeli {

    private static final Scanner scanner = new Scanner(System.in);

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly() {
        this.tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected String pyydaToisenPelaajanSiirtoa(Scanner scanner, String ekanSiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }
}
