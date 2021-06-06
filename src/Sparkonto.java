import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Sparkonto extends Konto {
    private char art;

    public Sparkonto(String kontoNummer, double habenZins, double kontoStand, Kunde myKunde,
            ArrayList<Kontobewegung> myBewegungen, char art, LocalDate kontoeroeffnung, double zinsenSumme) {
        super(kontoNummer, habenZins, kontoStand, myKunde, myBewegungen, kontoeroeffnung, zinsenSumme);
        this.art = art;
    }

    public void zinsenBerechnen(LocalDate datum) {
        updateZinsen(datum);
        addZinsen(this.zinsenSumme, datum);
    }

    protected void updateZinsen(LocalDate datum) {
        if (datum.getMonthValue() == 1 || datum.getMonthValue() == 4 || datum.getMonthValue() == 7
                || datum.getMonthValue() == 10 || datum.getMonthValue() == 12) {
            this.zinsenSumme = 0;
        }
        double kontoStand = this.kontoStand;
        Kontobewegung lastKontobewegung = (this.myBewegungen.size() != 0)
                ? this.myBewegungen.get(this.myBewegungen.size() - 1)
                : null;
        LocalDate lastDatum = lastKontobewegung.getDatum();
        long daysBetween = ChronoUnit.DAYS.between(lastDatum, datum);
        if (kontoStand > 0) {
            this.zinsenSumme += kontoStand * daysBetween / 365 * this.habenZins;}
    }
    public char getArt() {
        return this.art;
    }

    public void setArt(char art) {
        this.art = art;
    }
}
