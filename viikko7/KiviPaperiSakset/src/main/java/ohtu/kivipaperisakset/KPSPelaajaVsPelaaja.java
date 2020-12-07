package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSpeli {
    @Override
    protected String pyydaToisenPelaajanSiirtoa(Scanner scanner, String ekansiirto) {
        System.out.println("Toisen pelaajan siirto: ");
        return scanner.nextLine();
    }
}