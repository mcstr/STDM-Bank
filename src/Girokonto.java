import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
            double newkontoStand = this.kontoStand - betrag;
            this.kontoStand = newkontoStand;
            this.addKontoBewegung(-(betrag), datum, "Auszalung", this.kontoStand);
        }

    }

    public void zinsenBerechnen(LocalDate datum) {
        double kontoStand = 0;
        double sollZinsenPercent = this.sollZins/100;
        double sollZinsen = 0;
        double habenZinsenPercent = this.habenZins/100;
        double habenZinsen = 0;
        double finalZinsen = 0;
        ArrayList<Kontobewegung> bewegungInZeitraum = new ArrayList<Kontobewegung>();

        if (datum.getMonthValue() == 4) {
          for (Kontobewegung kontobewegung : this.myBewegungen) {
              if (kontobewegung.getDatum().getMonthValue() >= 1 && kontobewegung.getDatum().getMonthValue() < 4) {
                  bewegungInZeitraum.add(kontobewegung);
              }
          }
        } else if (datum.getMonthValue() == 7) {
            for (Kontobewegung kontobewegung : this.myBewegungen) {
                if (kontobewegung.getDatum().getMonthValue() >= 4 && kontobewegung.getDatum().getMonthValue() < 7) {
                    bewegungInZeitraum.add(kontobewegung);
                }
            }

        } else if (datum.getMonthValue() == 10) {
            for (Kontobewegung kontobewegung : this.myBewegungen) {
                if (kontobewegung.getDatum().getMonthValue() >= 7 && kontobewegung.getDatum().getMonthValue() < 10) {
                    bewegungInZeitraum.add(kontobewegung);
                }
            }
        } else {
            for (Kontobewegung kontobewegung : this.myBewegungen) {
                if (kontobewegung.getDatum().getMonthValue() >= 10 && kontobewegung.getDatum().getMonthValue() < 13) {
                    bewegungInZeitraum.add(kontobewegung);
                }
            }
        }




        for (int i = 0; i < bewegungInZeitraum.size(); i++) {
            Kontobewegung bewegung = bewegungInZeitraum.get(i);
            // Avoid trying to reach outside the ArrayList length
            Kontobewegung bewegung2 = i < bewegungInZeitraum.size() - 1 ? this.myBewegungen.get(i + 1) : null;

            if (bewegung2 != null) {
                kontoStand = bewegung.getKontoStand();

                    LocalDate datum1 = bewegung.getDatum();
                    LocalDate datum2 = bewegung2.getDatum();
                    long daysBetween = ChronoUnit.DAYS.between(datum1, datum2);

                    if (kontoStand > 0) {

                        double result = kontoStand * habenZinsenPercent * daysBetween / 365;
                        habenZinsen += result;

                    } else if (kontoStand < 0) {
                        double result = kontoStand * daysBetween / 365 * sollZinsenPercent;
                        sollZinsen += result;
                    }


            } else {
                kontoStand = bewegung.getKontoStand();
                    LocalDate datum1 = bewegung.getDatum();
                    long daysBetween = ChronoUnit.DAYS.between(datum1, datum);
                    if (kontoStand > 0) {
                        double result = kontoStand * habenZinsenPercent * daysBetween / 365;
                        habenZinsen += result;
                    } else if (kontoStand < 0) {
                        double result = kontoStand * sollZinsenPercent * daysBetween / 365;
                        sollZinsen += result;
                    }
            };
        }
        finalZinsen = habenZinsen + sollZinsen;
        addZinsen(finalZinsen, datum);
    }
}
