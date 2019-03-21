import java.util.ArrayList;
import java.util.List;

public class tavaKaart implements kaardid{

    private String isikukood;
    private double saldo;
    private String nimi;
    private int kliendinumber;
    private Klient klient;

    private List<String> tehingud = new ArrayList<>();


    public tavaKaart(double saldo, Klient klient) {
        this.klient = klient;
        isikukood = klient.getIsikukood();
        this.saldo = saldo;
        kliendinumber = klient.getKliendinumber();
        nimi = klient.getNimi();
    }

    public int getKliendinumber() {
        return kliendinumber;
    }

    public Klient getKlient() {
        return klient;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public String prindiKaardid() {
        return "Teil on tavakaart";
    }

    public void teostaÜlekanne(Klient klient,double summa) {
        saldo -= summa;
        tehingud.add("Ülekanne kontolt " + nimi + ", kontole " + klient.getNimi() + ", summa: " + summa);
        System.out.println("Ülekanne kontolt " + nimi + ", kontole " + klient.getNimi() + ", summa: " + summa);

    }

    public void teosteÜlekanneSuurenda(Klient klient,double summa) {
        saldo += summa;
        tehingud.add("Laekumine kontole " + nimi + ", kontolt " + klient.getNimi() + ", summa: "+ summa);
        System.out.println("Laekumine kontole " + nimi + ", kontolt " + klient.getNimi() + ", summa: "+ summa);

    }

    public double arvutaTehinguTasud() {

        return 0.1 * tehingud.size();
    }

    public List<String> getTehingud() {
        return tehingud;
    }

    @Override
    public String toString() {
        return nimi + " " + saldo;
    }



}