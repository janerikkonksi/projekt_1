import javax.swing.*;
import java.util.*;
@SuppressWarnings("Duplicates")

public class peaklass_uus extends Pank{

    static Pank pank = new Pank();

    public static void main(String[] args) {

        // list võimalike tegevuste numbritega
        ArrayList<Integer> tegevused = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,112));

        // väljastab ekraanile valitavad tegevused
        näita_tegevusi();

        // programm laseb kasutajal sisestada tegevuse ja seejärel käivitab tegevust realiseeriva meetodi
        while (true) {
            int tegevus = küsiarvu(tegevused);

            if (tegevus == 112){
                näita_tegevusi();
            }
            else if (tegevus == 1) {
               loo_klient();
            }
            else if (tegevus == 2) {
                lisa_kaart();
            }
            else if (tegevus == 3) {
                vaata_kaarte();
            }
            else if (tegevus == 4) {
                vaata_kliente();
            }
            else if (tegevus == 5) {
                ülekanne();
            }
            else if (tegevus == 6) { //panin lihtsalt, et kontrollida kas kontojäägid muutusid
                kliendid_saldo_järgi();
            }
            else if (tegevus == 10){
                System.out.println("Programm lõpetab töö.");
                return;
            }
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////  Siit algavad programmi tegevusi realiseerivad meetodid
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // väljastab ekraanile võimalikud tegevused
    public static void näita_tegevusi() {
        System.out.println("Valitavad tegevused:");
        System.out.println("\t1. loo klient"); //eraklient, äriklient?
        System.out.println("\t2. loo konto"); //  tavaKaart, kuldKaart
        System.out.println("\t3. näita kliendiga seotud kaarte");
        System.out.println("\t4. järjesta ning prindi kliendid tähestiku järjekorras");
        System.out.println("\t5. saada raha teisele kliendile");
        //teen siiani ise

        System.out.println("\t6. järjesta kliendid raha järgi");
        System.out.println("\t7. sularaha automaat"); //a) võta raha välja, vähenda lic, b) vaata kontojääki, c) näita tehinguid
        System.out.println("\t8. näita kliendi andmeid");
        System.out.println("\t9. kustuta klient");
        System.out.println("\t10. lõpeta töö");
        System.out.println("\t112. näita tegevusi");
        //ja mis iganes asjad veel
    }


    // laseb kasutajal sisestada uue tegevuse numbri
    public static int küsiarvu(ArrayList<Integer> tegevused){
        System.out.println("Sisesta tegevuse number: ");
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        if (tegevused.contains(i)){
            return i;
        }
        else {
            System.out.println("Ebasobiv number.");
            return küsiarvu(tegevused);
        }
    }


    // loob uue kliendi sisendina saadud nimest ja isikukoodist
    public static void loo_klient() {
        System.out.println("Sisesta nimi ning isikukood (tühik vahele) "); //see kliendinumber tuleb meelde jätta, selle järgi saab ülekandeid teha, kaarte lisada jne
        Scanner scan_kliendiandmed = new Scanner(System.in);
        String a = scan_kliendiandmed.nextLine();
        String[] tükelda = a.split(" ");
        if (tükelda.length == 2) {
            String nimi = tükelda[0];
            String isikukood = tükelda[1];

            //Kliendinumber genereeritakse juhuslikult
            int min = 100;
            int rand = (int) (Math.random() * 901) + min;

            if (pank.salvesta_isikukoodid.contains(isikukood) || pank.salvesta_kliendinumbrid.contains(rand)) {
                System.out.println("Selline klient on juba olemas");

            } else {
                pank.salvesta_isikukoodid.add(isikukood);
                pank.salvesta_kliendinumbrid.add(rand);
                Klient uus_klient = new Klient(nimi, isikukood, rand);
                System.out.println("Teie kliendinumber on " + rand);
                System.out.println("Jäta oma kliendinumber meelde!");
                pank.klientide_list.add(uus_klient);
            }
        }
    }


    // loob kliendile uue kaardi
    public static void lisa_kaart() {
        if (pank.klientide_list.size() != 0) {

            System.out.println("Millist kaarti soovite luua?");
            System.out.println("1. Tavakaart");
            System.out.println("2. Kuldkaart");
            Scanner vastus = new Scanner(System.in);
            int m = vastus.nextInt();

            System.out.println("Sisesta oma kliendinumber, et lisada sellele inimesele kaart");
            Scanner kliendinumber = new Scanner(System.in);
            int n = vastus.nextInt();
            if (pank.salvesta_kliendinumbrid.contains(n)) {
                int l = pank.salvesta_kliendinumbrid.indexOf(n);
                if (m == 1) {
                    tavaKaart uus_kaart = new tavaKaart(399, pank.klientide_list.get(l)); //Mdea kas summa saab klient ise määrata, palju panka paneb?
                    System.out.println("Tavakaart on loodud!");
                    pank.salvesta_kaardid.add(uus_kaart);
                } //nendele saab teha ilusti implements kaardid :)
                else if (m == 2) {
                    kuldKaart uus_kaart = new kuldKaart(399, pank.klientide_list.get(l));
                    System.out.println("Kuldkaart on loodud!");
                    pank.salvesta_kaardid.add(uus_kaart);
                }

            } else {
                System.out.println("Sellist klienti ei eksisteeri");
                System.out.println("Sisesta järgmine tegevus: ");
            }

        } else {
            System.out.println("Lisatud pole ühtegi klienti, lisa enne klient ning siis kaart");
        }
    }


    // väljastab antud kliendi kõik kaardid
    public static void vaata_kaarte() {
        System.out.println("Sisesta kliendinumber, et näha oma kaarte: ");
        Scanner sisend = new Scanner(System.in);
        int n = sisend.nextInt();
        if (pank.salvesta_kliendinumbrid.contains(n)) {
            try { //ok see try ja except mähib kui kaart puudub
                for (int o = 0; o < pank.salvesta_kaardid.size(); o++) {
                    if (pank.salvesta_kaardid.get(o).getKliendinumber() == n) {
                        System.out.println(pank.salvesta_kaardid.get(o).prindiKaardid());
                    }

                }
            } catch (Exception e) {
                System.out.println("Sellel kliendil ei eksisteeri kaarte");
            }
        } else {
            System.out.println("Sellist klienti ei eksisteeri");
        }
    }


    // väljastab ekraanile klientide listi tähestikulises järjekorras
    public static void vaata_kliente() {
        Collections.sort(pank.klientide_list);
        System.out.println("Klientide list tähestiku järjekorras: ");
        System.out.println(pank.klientide_list);
    }


    // teostab ülekande kahe kliendi vahel
    public static void ülekanne(){
        Scanner sisend = new Scanner(System.in);
        System.out.println("Sisesta oma kliendinumber:");
        int saatja = sisend.nextInt();
        System.out.println("Sisesta kliendinumber, kellele soovid kanda:");
        int saaja = sisend.nextInt();
        System.out.println("Sisesta summa, mida soovid kanda: ");
        Double saadetav_summa = sisend.nextDouble();

        if (pank.salvesta_kliendinumbrid.contains(saatja) && pank.salvesta_kliendinumbrid.contains(saaja)) {

            int salvesta_saatja = 0;
            int salvesta_saaja = 0;

            for (int o = 0; o < pank.salvesta_kaardid.size(); o++) {
                if (pank.salvesta_kaardid.get(o).getKliendinumber() == saatja) {
                    salvesta_saatja = o;
                } else if (pank.salvesta_kaardid.get(o).getKliendinumber() == saaja) {
                    salvesta_saaja = o;
                }
            }
            if (pank.salvesta_kaardid.get(salvesta_saatja).getSaldo() < saadetav_summa) {
                System.out.println("Kaardil pole sellise summa saatmiseks piisavalt raha.");
            } else {
                Klient saatja_isend = pank.salvesta_kaardid.get(salvesta_saatja).getKlient();
                Klient saaja_isend = pank.salvesta_kaardid.get(salvesta_saaja).getKlient();
                pank.salvesta_kaardid.get(salvesta_saatja).teostaÜlekanne(saaja_isend, saadetav_summa);
                pank.salvesta_kaardid.get(salvesta_saaja).teosteÜlekanneSuurenda(saatja_isend, saadetav_summa);
            }

        } else {
            System.out.println("Sellist saajat või saatjat ei eksisteeri");
        }

    }


    // väljastab midagi
    public static void kliendid_saldo_järgi() {
        for (kaardid a : pank.salvesta_kaardid) {
            System.out.println(a.getTehingud());
        }
        for (kaardid a : pank.salvesta_kaardid) {
            System.out.println(a);
        }
        for (Klient b : pank.klientide_list) {
            System.out.println(b);
        }
    }

}