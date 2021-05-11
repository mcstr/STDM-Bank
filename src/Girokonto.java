import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Girokonto extends Konto {
    private double sollZins;
    private double dispo;

    public Girokonto(String kontoNummer, double habenZins, double kontoStand, Kunde myKunde,
            ArrayList<Kontobewegung> myBewegungen, double sollZins, double dispo, LocalDate kontoeroeffnung) {
        super(kontoNummer, habenZins, kontoStand, myKunde, myBewegungen, kontoeroeffnung);
        this.dispo = dispo;
        this.sollZins = sollZins;
    }

    public void abheben(double betrag, LocalDate datum) {
        double total = this.kontoStand + this.dispo;
        String date = Main.format(datum);
        if (betrag > total) {
            String message = String.format(Locale.GERMANY,
                    "Kreditlimit von Konto %s überzogen. Buchung über -%,.2f Euro vom %s wurde nicht ausgeführt \r\n",
                    this.kontoNummer, betrag, date);
            System.err.println(message);
        } else {
            this.kontoStand -= betrag;
            this.addKontoBewegung(betrag, datum, "Auszalung");
        }

    }

    public void zinsenBerechnen() {
    }
}
