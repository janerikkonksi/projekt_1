import javax.swing.*;
import java.util.*;
@SuppressWarnings("Duplicates")

public class peaklass_uus extends Pank {

    public static void main(String[] args) throws InterruptedException {

        // klassi 'Pank' isend, mille kaudu saab kõiki pangaga seotud toiminguid teha
        Pank pank = new Pank();

        // klassi 'Sularahaautomaat' isend, mille kaudu saab teha sularahaautomaadiga seotud tegevusi
        Sularahaautomaat sularahaautomaat = new Sularahaautomaat(pank);

        Investeerimine investeerimine = new Investeerimine(pank);

        // listid võimalike tegevuste numbritega
        ArrayList<Integer> tegevused_pank = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 112));
        ArrayList<Integer> tegevused_sularahaautomaat = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> tegevused_investeerimine = new ArrayList<Integer>(Arrays.asList(1, 2, 3));


        // väljastab ekraanile valitavad tegevused
        näita_tegevusi();

        // programm laseb kasutajal sisestada tegevuse ja seejärel käivitab tegevust realiseeriva meetodi
        while (true) {
            int tegevus = küsiarvu(tegevused_pank);    // laseb kasutajal sisestada uue tegevuse numbri

            ////// TEGEVUSED //////
            if (tegevus == 112) {        // väljastab ekraanile võimalikud tegevused
                näita_tegevusi();
            } else if (tegevus == 1) {    // loob uue kliendi sisendina saadud nimest ja isikukoodist
                pank.loo_klient();
            } else if (tegevus == 2) {    // loob kliendile uue kaardi
                pank.lisa_kaart();
            } else if (tegevus == 3) {    // loob kliendile uue kaardi
                pank.vaata_kaarte();
            } else if (tegevus == 4) {    // väljastab ekraanile klientide listi tähestikulises järjekorras
                pank.vaata_kliente();
            } else if (tegevus == 5) {    // teostab ülekande kahe kliendi vahel
                pank.ülekanne();
            } else if (tegevus == 6) {    //panin lihtsalt, et kontrollida kas kontojäägid muutusid
                pank.kliendid_saldo_järgi();
            } else if (tegevus == 7) {     // imiteerib sularahaautomaati
                sularahaautomaat.sisesta_Kliendinumber();   // küsib kliendinumbri
                if (pank.salvesta_kliendinumbrid.contains(sularahaautomaat.kliendinumber)) {
                    while (true) {
                        sularahaautomaat.tegevused();      // väjastab ekraanile võimalikud tegevused
                        int tegevus2 = küsiarvu(tegevused_sularahaautomaat);    // küsib tegevuse numbri
                        if (tegevus2 == 1) {     // näitab kliendi kontojääki
                            sularahaautomaat.kontojääk();
                        } else if (tegevus2 == 2) {    // klient võtab sularaha välja
                            sularahaautomaat.raha_välja();
                        } else if (tegevus2 == 3) {    // klient paneb sularaha sisse
                            sularahaautomaat.raha_sisse();
                        } else if (tegevus2 == 4) {    // sularahaautomaadi töö lõppeb
                            sularahaautomaat.eemalda_kliendinumber();
                            System.out.println("Sularahaautomaat lõpetab tegevuse. ");
                            System.out.println();
                            Thread.sleep(1000);
                            näita_tegevusi();
                            break;
                        }
                    }
                }
            } else if (tegevus == 8) {
                pank.kliendi_andmed();
            } else if (tegevus == 10) {
                investeerimine.sisesta_Kliendinumber();
                if (pank.salvesta_kliendinumbrid.contains(investeerimine.kliendinumber)) {
                    while (true) {
                        investeerimine.võimalused();
                        int tegevus3 = küsiarvu(tegevused_investeerimine);
                        if (tegevus3 == 1) {
                            investeerimine.kontojääk();
                        } else if (tegevus3 == 2) {
                            investeerimine.investeering();
                        } else if (tegevus3 == 3) {
                            investeerimine.eemalda_kliendinumber();
                            System.out.println("Investeerimine on lõppenud");
                            System.out.println();
                            Thread.sleep(1000);
                            näita_tegevusi();
                            break;
                        }
                    }
                }

            } else if (tegevus == 11) {    // programm lõpetab töö
                System.out.println("Programm lõpetab töö.");
                return;
            }
        }
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

}
