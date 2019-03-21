import java.util.ArrayList;
import java.util.List;

public class Klient implements Comparable<Klient>  {
    private String nimi;
    private String isikukood;
    private int kliendinumber;
    private List<tavaKaart> tavakaardid = new ArrayList<>();
    private List<kuldKaart> kuldkaardid = new ArrayList<>();

    public Klient(String nimi, String isikukood, int kliendinumber) {
        this.nimi = nimi;
        this.isikukood = isikukood;
        this.kliendinumber = kliendinumber;
    }

    public int compareTo(Klient võrreldav) {
        return nimi.compareTo(võrreldav.nimi);
    }



    public String getNimi() {
        return nimi;
    }

    public String getIsikukood() {
        return isikukood;
    }

    public int getKliendinumber() {
        return kliendinumber;
    }

    //lisa kliendile tavakaart?
    public void lisatavaKaart(tavaKaart lisamiseks) {
        tavakaardid.add(lisamiseks);
    }

    @Override
    public String toString() {
        return "Klient: " + nimi + ", kliendinumber: " + kliendinumber;
    }
}
