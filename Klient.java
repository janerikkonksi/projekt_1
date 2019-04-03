import java.util.ArrayList;
import java.util.List;

public class Klient implements Comparable<Klient>  {

    private String nimi;
    private String isikukood;
    private int kliendinumber;
    private List<tavaKaart> tavakaardid = new ArrayList<>();
    private List<kuldKaart> kuldkaardid = new ArrayList<>();
    private double saldo;

    ///// KONSTRUKTOR /////
    public Klient(String nimi, String isikukood, int kliendinumber) {
        this.nimi = nimi;
        this.isikukood = isikukood;
        this.kliendinumber = kliendinumber;
        saldo = 0;
    }

    public int compareTo(Klient võrreldav) {
        return nimi.compareTo(võrreldav.nimi);
    }

    ///// GETTERID /////
    public String getNimi() {
        return nimi;
    }

    public String getIsikukood() {
        return isikukood;
    }

    public int getKliendinumber() {
        return kliendinumber;
    }

    public List<tavaKaart> getTavakaardid() {
        return tavakaardid;
    }

    public List<kuldKaart> getKuldkaardid() {
        return kuldkaardid;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Klient: " + nimi + ", kliendinumber: " + kliendinumber + ", saldo: " + saldo;
    }

    //lisa kliendile tavakaart?
    public void lisatavaKaart(tavaKaart lisamiseks) {
        tavakaardid.add(lisamiseks);
    }

    public void suurendaSaldot(double summa){
        saldo += summa;
    }

    public void vähendaSaldot(double summa){
        saldo -= summa;
    }


}
