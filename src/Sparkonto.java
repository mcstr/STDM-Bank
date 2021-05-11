import java.time.LocalDate;
import java.util.ArrayList;

public class Sparkonto extends Konto {
    private char art;

    public Sparkonto(String kontoNummer, double habenZins, double kontoStand, Kunde myKunde,
            ArrayList<Kontobewegung> myBewegungen, char art, LocalDate kontoeroeffnung) {
        super(kontoNummer, habenZins, kontoStand, myKunde, myBewegungen, kontoeroeffnung);
        this.art = art;
    }

    public void abheben(double betrag, LocalDate datum) {
        this.addKontoBewegung(betrag, datum, "Auszalung");
        this.kontoStand -= betrag;
    }

    public void zinsenBerechnen() {
    }

    public char getArt() {
        return this.art;
    }

    public void setArt(char art) {
        this.art = art;
    }
}
