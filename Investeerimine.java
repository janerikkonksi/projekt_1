import java.util.Random;
import java.util.Scanner;

class Investeerimine{

    private Pank pank;
    int kliendinumber;


    Investeerimine(Pank pank) {
        this.pank = pank;
    }

    void sisesta_Kliendinumber() throws InterruptedException {
        System.out.println("INVESTEERIMINE");
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

    void eemalda_kliendinumber(){
        this.kliendinumber = 0;
    }


    void võimalused() throws InterruptedException{
        Thread.sleep(1000);
        System.out.println();
        System.out.println("Valitavad tegevused:");
        System.out.println("\t1. vaata kontojääki");
        System.out.println("\t2. investeeri");
        System.out.println("\t3. lõpeta tegevus");
        Thread.sleep(1000);
    }
    void kontojääk(){
        Klient klient = pank.otsi_klient(kliendinumber);
        System.out.println("Teie kontojääk on: " + klient.getSaldo());
    }


    void investeering(){
        Scanner sisend = new Scanner(System.in);
        System.out.println("Sisesta rahasumma, mida soovid investeerida: ");
        Double summa = sisend.nextDouble();

        // otsib vastava kliendinumbriga kliendi isendi
        Klient klient = pank.otsi_klient(kliendinumber);



        // kontrollib kas kliendil on kontol piisavalt raha
        if(summa>klient.getSaldo()){
            System.out.println("Teil pole kontol piisavalt raha.");
        }

        else {
            double rangeMin = 15;
            double rangeMax = -15;

            System.out.println("Panustatud: " + summa);
            klient.vähendaSaldot(summa);
            System.out.println("Konto hetkeseis peale panustamist: " + klient.getSaldo());
            Random r = new Random();
            double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double investeerinultSaadudRaha = summa * Math.round(randomValue);

            if (investeerinultSaadudRaha > 0){
                klient.suurendaSaldot(investeerinultSaadudRaha);
                System.out.println("Tegite hea investeeringu ja teenisite: " + (investeerinultSaadudRaha));
                System.out.println("Teie uus kontojääk on: " + klient.getSaldo());
            }
            else {
                klient.suurendaSaldot(investeerinultSaadudRaha);
                System.out.println("Investeering ebaõnnestus ja kaotasite: " + (investeerinultSaadudRaha));
                System.out.println("Teie uus kontojääk on: " + klient.getSaldo());
            }
            if(klient.getSaldo()<0){
                System.out.println("Teie kontol vahendid puuduvad ");
            }

        }
    }

}
