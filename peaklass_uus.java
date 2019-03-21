import javax.swing.*;
import java.util.*;
@SuppressWarnings("Duplicates")
public class peaklass_uus {
    public static void main(String[] args) {

        System.out.println("1. loo klient"); //eraklient, äriklient?
        System.out.println("2. loo konto"); //tavaKaart, kuldKaart
        System.out.println("3. näita kliendiga seotud kaarte");
        System.out.println("4. järjesta ning prindi kliendid tähestiku järjekorras");
        System.out.println("5. saada raha teisele kliendile");
        //teen siiani ise

        System.out.println("6. järjesta kliendid raha järgi");
        System.out.println("7. sularaha automaat"); //a) võta raha välja, vähenda lic, b) vaata kontojääki, c) näita tehinguid
        System.out.println("8. näita kliendi andmeid");
        System.out.println("9. kustuta klient");
        System.out.println("10. lõpeta töö");
        //ja mis iganes asjad veel
        System.out.println("Sisesta valik: ");


        List<Klient> klientide_list = new ArrayList<>();
        List<kaardid> salvesta_kaardid = new ArrayList<>(); //interface põhjal luuakse list
        List<String> salvesta_isikukoodid = new ArrayList<>();
        List<Integer> salvesta_kliendinumbrid = new ArrayList<>();

        Scanner kliendinumber = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Scanner summa = new Scanner(System.in);
        int i = scan.nextInt();

        while (i != 10) {
            boolean tulemus = true;
            try {

            if (i == 1) {
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

                    if (salvesta_isikukoodid.contains(isikukood) || salvesta_kliendinumbrid.contains(rand)) {
                        System.out.println("Selline klient on juba olemas");
                        System.out.println("Sisesta järgmine tegevus: ");
                        i = scan.nextInt();

                    } else {
                        salvesta_isikukoodid.add(isikukood);
                        salvesta_kliendinumbrid.add(rand);
                        Klient uus_klient = new Klient(nimi, isikukood, rand);
                        System.out.println("Teie kliendinumber on " + rand);
                        System.out.println("Jäta oma kliendinumber meelde!");
                        klientide_list.add(uus_klient);
                        //System.out.println("Sisesta järgmine tegevus: ");
                        //i = scan.nextInt();
                    }
                }
            }

                 else if (i == 2) {
                    if (klientide_list.size() != 0) {

                        System.out.println("Millist kaarti soovite luua?");
                        System.out.println("1. Tavakaart");
                        System.out.println("2. Kuldkaart");
                        Scanner vastus = new Scanner(System.in);
                        int m = vastus.nextInt();
                        System.out.println("Sisesta oma kliendinumber, et lisada sellele inimesele kaart");
                        int n = kliendinumber.nextInt();
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

                            System.out.println("Sisesta järgmine tegevus: ");
                            i = scan.nextInt();

                        } else {
                            System.out.println("Sellist klienti ei eksisteeri");
                            System.out.println("Sisesta järgmine tegevus: ");
                            i = scan.nextInt();
                        }

                    } else {
                        System.out.println("Lisatud pole ühtegi klienti, lisa enne klient ning siis kaart");
                        System.out.println("Sisesta järgmine tegevus: ");
                        i = scan.nextInt();
                    }
                } else if (i == 3) {
                    System.out.println("Sisesta kliendinumber, et näha oma kaarte: ");
                    int n = kliendinumber.nextInt();
                    if (salvesta_kliendinumbrid.contains(n)) {
                        try { //ok see try ja except mähib kui kaart puudub
                            for (int o = 0; o < salvesta_kaardid.size(); o++) {
                                if (salvesta_kaardid.get(o).getKliendinumber() == n) {
                                    System.out.println(salvesta_kaardid.get(o).prindiKaardid());
                                }

                            }
                            System.out.println("Sisesta järgmine tegevus: ");
                            i = scan.nextInt();
                        } catch (Exception e) {
                            System.out.println("Sellel kliendil ei eksisteeri kaarte");
                            System.out.println("Sisesta järgmine tegevus: ");
                            i = scan.nextInt();
                        }
                    } else {
                        System.out.println("Sellist klienti ei eksisteeri");
                        System.out.println("Sisesta järgmine tegevus: ");
                        i = scan.nextInt();
                    }
                } else if (i == 4) {
                    Collections.sort(klientide_list);
                    System.out.println("Klientide list tähestiku järjekorras: ");
                    System.out.println(klientide_list);
                    System.out.println("Sisesta järgmine tegevus: ");
                    i = scan.nextInt();
                } else if (i == 5) {
                    System.out.println("Sisesta oma kliendinumber:");
                    int saatja = kliendinumber.nextInt();
                    System.out.println("Sisesta kliendinumber, kellele soovid kanda:");
                    int saaja = kliendinumber.nextInt();
                    System.out.println("Sisesta summa, mida soovid kanda: ");
                    Double saadetav_summa = summa.nextDouble();

                    if (salvesta_kliendinumbrid.contains(saatja) && salvesta_kliendinumbrid.contains(saaja)) {

                        int salvesta_saatja = 0;
                        int salvesta_saaja = 0;

                        for (int o = 0; o < salvesta_kaardid.size(); o++) {
                            if (salvesta_kaardid.get(o).getKliendinumber() == saatja) {
                                salvesta_saatja = o;
                            } else if (salvesta_kaardid.get(o).getKliendinumber() == saaja) {
                                salvesta_saaja = o;
                            }
                        }
                        if (salvesta_kaardid.get(salvesta_saatja).getSaldo() < saadetav_summa) {
                            System.out.println("Kaardil pole sellise summa saatmiseks piisavalt rahhi");
                        } else {
                            Klient saatja_isend = salvesta_kaardid.get(salvesta_saatja).getKlient();
                            Klient saaja_isend = salvesta_kaardid.get(salvesta_saaja).getKlient();
                            salvesta_kaardid.get(salvesta_saatja).teostaÜlekanne(saaja_isend, saadetav_summa);
                            salvesta_kaardid.get(salvesta_saaja).teosteÜlekanneSuurenda(saatja_isend, saadetav_summa);
                        }


                        System.out.println("Sisesta järgmine tegevus: ");
                        i = scan.nextInt();
                    } else {
                        System.out.println("Sellist saajat või saatjat ei eksisteeri");
                        System.out.println("Sisesta järgmine tegevus: ");
                        i = scan.nextInt();
                    }

                }
                if (i == 6) { //panin lihtsalt, et kontrollida kas kontojäägid muutusid
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
            } catch (Exception e)    {
                System.out.println("Midagi läks valesti");
            }
            finally {
                System.out.println("Sisesta järgmine tegevus: ");
                i = scan.nextInt();
            }
        }
    }
}

/// while True:
//     proovi küsida arvu
//      kui ebaõnnestub, siis teatame
//     kui õnnestub, peatame tsükli : break
