import java.util.List;

public interface kaardid {

    String prindiKaardid() ;

    void teostaÜlekanne(Klient klient, double summa) ;

    int getKliendinumber() ;

    Klient getKlient();

    void teostaÜlekanneSuurenda(Klient klient, double summa);

    double getSaldo();

    List<String> getTehingud() ;
}
