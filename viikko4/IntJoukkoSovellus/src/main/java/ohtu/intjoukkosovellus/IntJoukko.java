package ohtu.intjoukkosovellus;

import java.security.InvalidParameterException;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,
                            OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] ljono;
    private int alkioidenLkm;

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new InvalidParameterException("Kapasiteetin tulee olla positiivinen luku");
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new InvalidParameterException("Parametrien tulee olla positiivisia");
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;

            if (alkioidenLkm % ljono.length == 0) {
                int[] taulukkoOld = ljono;
                ljono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, ljono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean lukuLoytyi = false;

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                lukuLoytyi = true;
                alkioidenLkm--;
            }

            if (lukuLoytyi) {
                ljono[i] = ljono[i + 1];
            }
        }

        if ( lukuLoytyi ) return true;
        else return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String mjono = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            mjono += ljono[i];
            if (i != alkioidenLkm -1) {
                mjono += ", ";
            }
        }
        mjono += "}";
        return mjono;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        return joukkoOperaatio(a, b, true, true, true);
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        return joukkoOperaatio(a, b, false, true, false);
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        return joukkoOperaatio(a, b, true, false, false);
    }

    private static IntJoukko joukkoOperaatio( IntJoukko a, IntJoukko b,
              boolean lisaaVainA, boolean lisaaMolemmat, boolean lisaaVainB){
        IntJoukko uusi = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i]) && lisaaMolemmat) {
                uusi.lisaa(aTaulu[i]);
            } else if (!b.kuuluu(aTaulu[i]) && lisaaVainA) {
                uusi.lisaa(aTaulu[i]);
            }
        }
        for (int i = 0; i < bTaulu.length; i++) {
            if (!a.kuuluu(bTaulu[i]) && lisaaVainB) {
                uusi.lisaa(bTaulu[i]);
            }
        }

        return uusi;
    }
}
