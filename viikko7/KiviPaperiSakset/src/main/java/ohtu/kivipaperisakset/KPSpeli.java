package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSpeli {

    private static final Scanner scanner = new Scanner(System.in);

    public static KPSPelaajaVsPelaaja luoPelaajaVsPelaajaKPS() {
        return new KPSPelaajaVsPelaaja();
    }

    public static KPSTekoaly luoTekoalyKPS() {
        return new KPSTekoaly();
    }

    public static KPSParempiTekoaly luoParempiTekoalyKPS() {
        return new KPSParempiTekoaly();
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        System.out.println("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();

        String tokanSiirto = pyydaToisenPelaajanSiirtoa(scanner, ekanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            tokanSiirto = pyydaToisenPelaajanSiirtoa(scanner, ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected abstract String pyydaToisenPelaajanSiirtoa(Scanner scanner, String ekanSiirto);
}
