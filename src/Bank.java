
import java.util.ArrayList;
import java.util.Locale;


public class Bank {

    static String blz = "500 401 50";
    static String institutsName = "Sparkasse Münster";
    private  static ArrayList<Kunde> myKunden = new ArrayList<Kunde>();
    private  static ArrayList<Konto> myKonten = new ArrayList<Konto>();

    public static ArrayList<Kunde> getKunden() {
        return myKunden;
    }

    public static ArrayList<Konto> getKonten() {
        return myKonten;
    }


    public static Kunde getKunde(String kundenNummer) {

        Kunde foundKunde = null;
        for (Kunde kunde : myKunden) {
            if(kunde.getKundenNummer().equals(kundenNummer)) {
                foundKunde = kunde;
            }

        }
        return foundKunde;
    }


    public static Konto getKonto(String kontoNummer) {

        Konto foundKonto = null;
        for (Konto konto : myKonten) {
            if(konto.kontoNummer.equals(kontoNummer)) {
                return konto;
            }
        }
        return foundKonto;
    }

    public static void printKundeInfo(String kundenNummer) {

       Kunde kunde =  getKunde(kundenNummer);
       String name = kunde.getName();
       String addresse = kunde.getAddresse();
       String kundenSeit = Main.format(kunde.getKundenSeit());
       ArrayList<String> kontos = new ArrayList<String>();

       for (Konto konto : kunde.getMyKonten()) {
           kontos.add(konto.kontoNummer);
       }
       String message = String.format(
               "Name: %s \r\nAddresse: %s \r\nKunden seit: %s \r\nKontos: %s\r\n",
               name, addresse, kundenSeit, String.join(",", kontos));
       System.out.println(message);
    }


    public static void printKontoInfo(String kontoNummer) {

        Konto konto = getKonto(kontoNummer);
        String kunde = konto.myKunde.getName();
        double kontoStand = konto.kontoStand;

        String message = String.format(Locale.GERMANY,
                "Kontoinhaber: %s \r\nKto-Nr.: %s, \r\nKontostand: %,.2f Euro\r\n",
                kunde, kontoNummer, kontoStand);
        System.out.println(message);

    }

    public static void addKunde(Kunde myKunden) {
        Bank.myKunden.add(myKunden);
    }

    public static void addKonto(Konto myKonto) {
        myKonten.add(myKonto);
    }
}
