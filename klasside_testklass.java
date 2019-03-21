import java.util.ArrayList;
import java.util.List;

public class klasside_testklass {
    public static void main(String[] args) {
        Klient Mari = new Klient("Mari", "39906", 599);

        tavaKaart uus = new tavaKaart(3.9, Mari);
        System.out.println(uus);
        List<Klient> uus2 = new ArrayList<>();

    }
}
