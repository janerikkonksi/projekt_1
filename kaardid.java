public interface kaardid {
    String prindiKaardid() ;

    void teostaÜlekanne(Klient klient, double summa) ;

    int getKliendinumber() ;

    Klient getKlient();

    void teosteÜlekanneSuurenda(Klient klient, double summa);

    double getSaldo();
}
