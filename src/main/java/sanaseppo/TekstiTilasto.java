package sanaseppo;

/**
 * TekstiTilasto-luokka sisältää metodeja annetun tekstin sisältämien sanojen ja
 * rivien laskemiseen, sekä annetun indeksin sijainnin määrittämiseen tekstissä.
 */
public abstract class TekstiTilasto {

    /**
     * Palauttaa millä rivillä tietty indeksi sijaitsee tekstissä kokonaislukuna
     *
     * @param paikka Indeksi, jonka sijaintiriviä haetaan
     * @param teksti Teksti, jonka indeksille haetaan sijaintirivi
     * @return Rivi, jolla indeksi sijaitsee tekstissä
     */
    public static int nykyRivi(int paikka, String teksti) {
        return laskeRivit(teksti.substring(0, paikka));
    }

    /**
     * Palauttaa tekstissä olevien whitespacella erotettujen sanojen lukumäärän
     *
     * @param teksti Teksti, jonka sanojen lukumäärä halutaan
     * @return Sanojen lukumäärä kokonaislukuna
     */
    public static int laskeSanat(String teksti) {
        int sanat = 0;
        for (int i = 0; i < teksti.length(); i++) {
            //lisätään sana jos:
            //on tekstin ensimmäinen merkki, eikä ole whitespace
            //ei ole ensimmäinen merkki, lisätään sana, jos ei ole whitespace, mutta edellinen merkki oli
            if (i == 0 && !Character.isWhitespace(teksti.charAt(i))) {
                sanat++;
            } else if (i > 0 && !Character.isWhitespace(teksti.charAt(i)) && Character.isWhitespace(teksti.charAt(i - 1))) {
                sanat++;
            }
        }
        return sanat;
    }

    /**
     * Palauttaa tekstin sisältämien rivien lukumäärän
     * 
     * @param teksti Teksti, jonka sisältämien rivien lukumäärä halutaan
     * @return Rivien lukumäärä kokonaislukuna
     */
    public static int laskeRivit(String teksti) {
        int rivit = 1;
        for (int i = 0; i < teksti.length(); i++) {
            //"."-merkkijono matchaa kaiken muun, paitsi rivinvaihtojen kanssa.
            if (!String.valueOf(teksti.charAt(i)).matches(".")) {
                rivit++;
            }
        }
        return rivit;
    }

    /**
     * Palauttaa annetun indeksin sijainnin omalla rivillään tekstissä
     * 
     * @param paikka indeksi, jonka sijainti halutaan määrittää
     * @param teksti teksti, jossa indeksi sijaitsee
     * @return monesko merkki indeksin osoittama merkki on omalla rivillään tekstissä. Palauttaa -1, jos indeksi ei ole validi.
     */
    public static int laskeIndeksi(int paikka, String teksti) {
        if (paikka > teksti.length() || paikka < 0) {
            return -1;
        }
        //Käydään teksti takaperin indeksistä paikka. Jos tulee rivinvaihto tai tekstin alku vastaan, palautetaan tulos
        int indeksi = 0;
        while (paikka > 0) {
            paikka--;
            //"."-merkkijono matchaa kaiken muun, paitsi rivinvaihtojen kanssa.
            if (!String.valueOf(teksti.charAt(paikka)).matches(".")) {
                return indeksi;
            }
            indeksi++;
        }
        return indeksi;
    }
}
