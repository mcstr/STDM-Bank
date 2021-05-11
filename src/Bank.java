
import java.util.ArrayList;


public class Bank {
    static String blz = "500 401 50";
    static String institutsName = "Sparkasse Münster";
    public static ArrayList<Kunde> myKunden = new ArrayList<Kunde>();
    public static ArrayList<Konto> myKonten = new ArrayList<Konto>();

    public Bank() {
    }

    public static void addKunde(Kunde myKunden) {
        Bank.myKunden.add(myKunden);
    }

    public static void addKonto(Konto myKonto) {
        myKonten.add(myKonto);
    }
}
