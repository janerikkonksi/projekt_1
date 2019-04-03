import java.util.Scanner;

public class Sularahaautomaat {

    Pank pank;
    int kliendinumber;

    public Sularahaautomaat(Pank pank) {
        this.pank = pank;
    }

    public void sisesta_Kliendinumber() throws InterruptedException {
        System.out.println("SULARAHAAUTOMAAT");
        Thread.sleep(1000);
        Scanner sisend = new Scanner(System.in);
        System.out.println("Jätkamiseks sisesta enda kliendinumber:");
        int kliendi_nr = sisend.nextInt();
        // kontrollib, kas klient on olemas
        if (pank.salvesta_kliendinumbrid.contains(kliendi_nr)) {
            Klient klient = pank.otsi_klient(kliendi_nr);
            System.out.println("Tere, " + klient.getNimi() + "!");
            this.kliendinumber = kliendi_nr;
        }
        else{
            System.out.println("Sellise kliendinumbriga klienti pole: ");
        }
    }

    public void eemalda_kliendinumber(){
        this.kliendinumber = 0;
    }

    public void tegevused() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println();
        System.out.println("Valitavad tegevused:");
        System.out.println("\t1. vaata kontojääki");
        System.out.println("\t2. võta sularaha välja");
        System.out.println("\t3. pane sularaha kontole");
        System.out.println("\t4. lõpeta tegevus");
        Thread.sleep(1000);
    }


    public void kontojääk(){
        Klient klient = pank.otsi_klient(kliendinumber);
        System.out.println("Teie kontojääk on: " + klient.getSaldo());
    }


    public void raha_välja(){
        Scanner sisend = new Scanner(System.in);
        System.out.println("Sisesta rahasumma, mida soovid kontolt välja võtta: ");
        Double summa = sisend.nextDouble();

        // otsib vastava kliendinumbriga kliendi isendi
        Klient klient = pank.otsi_klient(kliendinumber);

        // kontrollib kas kliendil on kontol piisavalt raha
        if(summa>klient.getSaldo()){
            System.out.println("Teil pole kontol piisavalt raha.");
        }
        else {
            klient.vähendaSaldot(summa);
            System.out.println("Välja võetud summa: " + summa);
            System.out.println("Teie uus kontojääk: " + klient.getSaldo());
        }
    }


    public void raha_sisse(){
        Scanner sisend = new Scanner(System.in);
        System.out.println("Sisesta rahasumma, mida soovid oma kontole panna: ");
        Double summa = sisend.nextDouble();

        // otsib vastava kliendinumbriga kliendi isendi
        Klient klient = pank.otsi_klient(kliendinumber);

        klient.suurendaSaldot(summa);
        System.out.println("Teie kontole lisandunud summa: " + summa);
        System.out.println("Teie uus kontojääk: " + klient.getSaldo());
    }

}
