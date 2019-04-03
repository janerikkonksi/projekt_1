import java.util.ArrayList;
import java.util.List;

public class tavaKaart implements kaardid{

    private String isikukood;
    private double saldo;
    private String nimi;
    private int kliendinumber;
    private Klient klient;
    private List<String> tehingud = new ArrayList<>();

    ///// KONSTRUKTOR /////
    public tavaKaart(double saldo, Klient klient) {
        this.klient = klient;
        isikukood = klient.getIsikukood();
        this.saldo = saldo;
        kliendinumber = klient.getKliendinumber();
        nimi = klient.getNimi();
    }

    ///// GETTERID /////
    public int getKliendinumber() {
        return kliendinumber;
    }

    public Klient getKlient() {
        return klient;
    }

    public List<String> getTehingud() {
        return tehingud;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public String prindiKaardid() {
        return "Teil on tavakaart";
    }

    @Override
    public String toString() {
        return nimi + " " + saldo;
    }


    public void teostaÜlekanne(Klient klient,double summa) {
        saldo -= summa;     //kaardi saldo vähendamine
        klient.vähendaSaldot(summa);    //kliendi saldo vähendamine
        tehingud.add("Ülekanne kontolt " + nimi + ", kontole " + klient.getNimi() + ", summa: " + summa);
        System.out.println("Ülekanne kontolt " + nimi + ", kontole " + klient.getNimi() + ", summa: " + summa);
    }

    public void teostaÜlekanneSuurenda(Klient klient,double summa) {
        saldo += summa;     //kaardi saldo suurendamine
        klient.suurendaSaldot(summa);   //kliendi saldo suurendamine
        tehingud.add("Laekumine kontole " + nimi + ", kontolt " + klient.getNimi() + ", summa: "+ summa);
        System.out.println("Laekumine kontole " + nimi + ", kontolt " + klient.getNimi() + ", summa: "+ summa);
    }



}