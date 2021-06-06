import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class Girokonto extends Konto {
    private double sollZins;
    private double dispo;

    public Girokonto(String kontoNummer, double habenZins, double kontoStand, Kunde myKunde,
            ArrayList<Kontobewegung> myBewegungen, double sollZins, double dispo, LocalDate kontoeroeffnung, double zinsenSumme) {
        super(kontoNummer, habenZins, kontoStand, myKunde, myBewegungen, kontoeroeffnung, zinsenSumme);
        this.dispo = dispo;
        this.sollZins = sollZins/100;
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
            double newkontoStand = this.kontoStand - betrag;
            this.kontoStand = newkontoStand;
            this.addKontoBewegung(-(betrag), datum, "Auszalung", this.kontoStand);
        }

    }

    public void zinsenBerechnen(LocalDate datum) {
        updateZinsen(datum);
        addZinsen(this.zinsenSumme, datum);
        // System.out.println(this.zinsenSumme);
    }

    protected void updateZinsen (LocalDate datum) {
        if (datum.getMonthValue() == 1 || datum.getMonthValue() == 4 || datum.getMonthValue() == 7 || datum.getMonthValue() == 10 || datum.getMonthValue() == 12) {
            this.zinsenSumme = 0;
        }
        double kontoStand = this.kontoStand;
        Kontobewegung lastKontobewegung = (this.myBewegungen.size() != 0) ? this.myBewegungen.get(this.myBewegungen.size() - 1) : null;
        LocalDate lastDatum = lastKontobewegung.getDatum();
        long daysBetween = ChronoUnit.DAYS.between(lastDatum, datum);
        if (kontoStand > 0) {
            this.zinsenSumme += kontoStand * daysBetween / 365 * this.habenZins;
        } else if (kontoStand < 0) {
            this.zinsenSumme -= kontoStand * daysBetween / 365 * this.sollZins;
        }
    }
}
