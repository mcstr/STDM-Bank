import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Sparkonto extends Konto {
    private char art;

    public Sparkonto(String kontoNummer, double habenZins, double kontoStand, Kunde myKunde,
            ArrayList<Kontobewegung> myBewegungen, char art, LocalDate kontoeroeffnung) {
        super(kontoNummer, habenZins, kontoStand, myKunde, myBewegungen, kontoeroeffnung);
        this.art = art;
    }

    public void zinsenBerechnen(LocalDate datum) {
        double kontoStand = 0;
        double habenZinsenPercent = this.habenZins / 100;
        double habenZinsen = 0;

        for (int i = 0; i < this.myBewegungen.size(); i++) {
            Kontobewegung bewegung = this.myBewegungen.get(i);
            // Avoid trying to reach outside the ArrayList length
            Kontobewegung bewegung2 = i < this.myBewegungen.size() - 1 ? this.myBewegungen.get(i + 1) : null;;


            if (bewegung2 != null) {
                kontoStand = bewegung.getKontoStand();
                LocalDate datum1 = bewegung.getDatum();
                LocalDate datum2 = bewegung2.getDatum();
                long daysBetween = ChronoUnit.DAYS.between(datum1, datum2);
                double result = kontoStand * habenZinsenPercent * daysBetween / 365;
                habenZinsen += result;
            } else {
                kontoStand = bewegung.getKontoStand();
                LocalDate datum1 = bewegung.getDatum();
                long daysBetween = ChronoUnit.DAYS.between(datum1, datum);
                double result = kontoStand * habenZinsenPercent * daysBetween / 365;
                habenZinsen += result;
            }
        }
        addZinsen(habenZinsen, datum);
    }

    public char getArt() {
        return this.art;
    }

    public void setArt(char art) {
        this.art = art;
    }
}
