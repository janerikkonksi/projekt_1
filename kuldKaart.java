public class kuldKaart extends tavaKaart implements kaardid {

    public kuldKaart(double saldo, Klient klient) {
        super(saldo, klient);
    }

    @Override
    public String prindiKaardid() {
        return "Teil on kuldkaart";
    }

    public void teostaÜlekanne(Klient kontole, double summa) {

    }

    @Override
    public String toString() {
        return "Kliendil " + getKliendinumber() + " on kuldkaart";
    }
}
