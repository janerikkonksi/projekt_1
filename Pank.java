import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Pank{

    public  List<Klient> klientide_list = new ArrayList<>();
    public  List<kaardid> salvesta_kaardid = new ArrayList<>(); //interface põhjal luuakse list
    public  List<String> salvesta_isikukoodid = new ArrayList<>();
    public  List<Integer> salvesta_kliendinumbrid = new ArrayList<>();

    // tühi konstruktor
    public Pank() {
    }

    public List<kaardid> getSalvesta_kaardid() {
        return salvesta_kaardid;
    }

    ///////////////////////////////////////////////////////////////
    //                         TEGEVUSED                         //
    ///////////////////////////////////////////////////////////////

    // 112.
    // väljastab ekraanile võimalikud tegevused
    public static void näita_tegevusi() throws InterruptedException {
        System.out.println("Valitavad tegevused:");
        System.out.println("\t1. loo klient"); //eraklient, äriklient?
        System.out.println("\t2. loo konto"); //  tavaKaart, kuldKaart
        System.out.println("\t3. näita kliendiga seotud kaarte");
        System.out.println("\t4. järjesta ning prindi kliendid tähestiku järjekorras");
        System.out.println("\t5. saada raha teisele kliendile");
        //teen siiani ise

        System.out.println("\t6. järjesta kliendid saldo järgi");
        System.out.println("\t7. sularahaautomaat");
        System.out.println("\t8. näita kliendi andmeid");
        System.out.println("\t9. eemalda klient");
        System.out.println("\t10. lõpeta töö");
        System.out.println("\t112. näita tegevusi");
        Thread.sleep(1000);
        //ja mis iganes asjad veel
    }

    // 1.
    // loob uue kliendi sisendina saadud nimest ja isikukoodist
    public void loo_klient() {
        System.out.println("Sisesta nimi ning isikukood (tühik vahele) "); //see kliendinumber tuleb meelde jätta, selle järgi saab ülekandeid teha, kaarte lisada jne
        Scanner scan_kliendiandmed = new Scanner(System.in);
        String a = scan_kliendiandmed.nextLine();
        String[] tükelda = a.split(" ");
        if (tükelda.length == 2) {
            String nimi = tükelda[0];
            String isikukood = tükelda[1];

            //Kliendinumber genereeritakse juhuslikult
            int min = 100;
            int rand = (int)(Math.random() * 901) + min;

            if (salvesta_isikukoodid.contains(isikukood) || salvesta_kliendinumbrid.contains(rand)) {
                System.out.println("Selline klient on juba olemas");
            }
            else {
                salvesta_isikukoodid.add(isikukood);
                salvesta_kliendinumbrid.add(rand);
                Klient uus_klient = new Klient(nimi, isikukood, rand);
                System.out.println("Teie kliendinumber on " + rand);
                System.out.println("Jäta oma kliendinumber meelde!");
                klientide_list.add(uus_klient);
            }
        }
    }

    // 2.
    // loob kliendile uue kaardi
    public void lisa_kaart() {

        if (klientide_list.size() != 0) {
            System.out.println("Millist kaarti soovite luua?");
            System.out.println("\t1. Tavakaart");
            System.out.println("\t2. Kuldkaart");
            Scanner vastus = new Scanner(System.in);
            int m = vastus.nextInt();
            System.out.println("Sisesta oma kliendinumber, et lisada sellele inimesele kaart");
            int n = vastus.nextInt();

            if (salvesta_kliendinumbrid.contains(n)) {
                int l = salvesta_kliendinumbrid.indexOf(n);
                if (m == 1) {
                    tavaKaart uus_kaart = new tavaKaart(399, klientide_list.get(l)); //Mdea kas summa saab klient ise määrata, palju panka paneb?
                    System.out.println("Tavakaart on loodud!");
                    salvesta_kaardid.add(uus_kaart);
                } //nendele saab teha ilusti implements kaardid :)
                else if (m == 2) {
                    kuldKaart uus_kaart = new kuldKaart(399, klientide_list.get(l));
                    System.out.println("Kuldkaart on loodud!");
                    salvesta_kaardid.add(uus_kaart);
                }
                else{
                    System.out.println("Sellist tegevust ei saa valida.");
                }
            }
            else {
                System.out.println("Sellist klienti ei eksisteeri");
                System.out.println("Sisesta järgmine tegevus: ");
            }
        }
        else {
            System.out.println("Lisatud pole ühtegi klienti, lisa enne klient ning siis kaart");
        }
    }

    // 3.
    // väljastab antud kliendi kõik kaardid
    public  void vaata_kaarte() {
        System.out.println("Sisesta kliendinumber, et näha oma kaarte: ");
        Scanner sisend = new Scanner(System.in);
        int n = sisend.nextInt();
        if (salvesta_kliendinumbrid.contains(n)) {
            try { //ok see try ja except mähib kui kaart puudub
                for (int o = 0; o < salvesta_kaardid.size(); o++) {
                    if (salvesta_kaardid.get(o).getKliendinumber() == n) {
                        System.out.println(salvesta_kaardid.get(o).prindiKaardid());
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Sellel kliendil ei eksisteeri kaarte");
            }
        }
        else {
            System.out.println("Sellist klienti ei eksisteeri");
        }
    }

    // 4.
    // väljastab ekraanile klientide listi tähestikulises järjekorras
    public  void vaata_kliente() {
        Collections.sort(klientide_list);
        System.out.println("Klientide list tähestiku järjekorras: ");
        System.out.println(klientide_list);
    }

    // 5.
    // teostab ülekande kahe kliendi vahel
    public void ülekanne(){
        Scanner sisend = new Scanner(System.in);
        System.out.println("Sisesta oma kliendinumber:");
        int saatja = sisend.nextInt();
        System.out.println("Sisesta kliendinumber, kellele soovid kanda:");
        int saaja = sisend.nextInt();
        System.out.println("Sisesta summa, mida soovid kanda: ");
        Double saadetav_summa = sisend.nextDouble();

        if (salvesta_kliendinumbrid.contains(saatja) && salvesta_kliendinumbrid.contains(saaja)) {

            int salvesta_saatja = 0;
            int salvesta_saaja = 0;

            for (int o = 0; o < salvesta_kaardid.size(); o++) {
                if (salvesta_kaardid.get(o).getKliendinumber() == saatja) {
                    salvesta_saatja = o;
                }
                else if (salvesta_kaardid.get(o).getKliendinumber() == saaja) {
                    salvesta_saaja = o;
                }
            }
            if (salvesta_kaardid.get(salvesta_saatja).getSaldo() < saadetav_summa) {
                System.out.println("Kaardil pole sellise summa saatmiseks piisavalt raha.");
            }
            else {
                Klient saatja_isend = salvesta_kaardid.get(salvesta_saatja).getKlient();
                Klient saaja_isend = salvesta_kaardid.get(salvesta_saaja).getKlient();
                salvesta_kaardid.get(salvesta_saatja).teostaÜlekanne(saaja_isend, saadetav_summa);
                salvesta_kaardid.get(salvesta_saaja).teostaÜlekanneSuurenda(saatja_isend, saadetav_summa);
            }

        } else {
            System.out.println("Sellist saajat või saatjat ei eksisteeri");
        }

    }

    // 6.
    // väljastab midagi
    public void kliendid_saldo_järgi() {
        for (kaardid a : salvesta_kaardid) {
            System.out.println(a.getTehingud());
        }
        for (kaardid a : salvesta_kaardid) {
            System.out.println(a);
        }
        for (Klient b : klientide_list) {
            System.out.println(b);
        }
    }

    // 8.
    // väljastab kliendiga seotud andmed
    public void kliendi_andmed(){
        Scanner sisend = new Scanner(System.in);
        System.out.println("Sisesta oma kliendinumber:");
        int kliendi_nr = sisend.nextInt();
        Klient klient = otsi_klient(kliendi_nr);
        System.out.println(klient.toString());
    }



    // otsib klientide listist vastava kliendinumbriga kliendi välja
    public Klient otsi_klient(int kliendi_nr){
        Klient otsitav_klient = new Klient("","",0);
        for(Klient klient : klientide_list){
            if(klient.getKliendinumber()==kliendi_nr){
                otsitav_klient=klient;
            }
        }
       return otsitav_klient;
    }

}
