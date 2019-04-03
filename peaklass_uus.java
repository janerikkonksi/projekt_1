import javax.swing.*;
import java.util.*;
@SuppressWarnings("Duplicates")

public class peaklass_uus extends Pank{

    public static void main(String[] args) {

        // klassi 'Pank' isend, mille kaudu saab kõiki toiminguid teha
        Pank pank = new Pank();

        // list võimalike tegevuste numbritega
        ArrayList<Integer> tegevused = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,112));

        // väljastab ekraanile valitavad tegevused
        pank.näita_tegevusi();

        // programm laseb kasutajal sisestada tegevuse ja seejärel käivitab tegevust realiseeriva meetodi
        while (true) {
            int tegevus = küsiarvu(tegevused);    // laseb kasutajal sisestada uue tegevuse numbri

            ////// TEGEVUSED //////
            if (tegevus == 112){        // väljastab ekraanile võimalikud tegevused
                pank.näita_tegevusi();
            }
            else if (tegevus == 1) {    // loob uue kliendi sisendina saadud nimest ja isikukoodist
                pank.loo_klient();
            }
            else if (tegevus == 2) {    // loob kliendile uue kaardi
                pank.lisa_kaart();
            }
            else if (tegevus == 3) {    // loob kliendile uue kaardi
                pank.vaata_kaarte();
            }
            else if (tegevus == 4) {    // väljastab ekraanile klientide listi tähestikulises järjekorras
                pank.vaata_kliente();
            }
            else if (tegevus == 5) {    // teostab ülekande kahe kliendi vahel
                pank.ülekanne();
            }
            else if (tegevus == 6) {    //panin lihtsalt, et kontrollida kas kontojäägid muutusid
                pank.kliendid_saldo_järgi();
            }
            else if (tegevus == 10){    // programm lõpetab töö
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