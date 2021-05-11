import java.time.LocalDate;

public class Kontobewegung {
    private double betrag;
    private LocalDate datum;
    private Konto myKonto;
    private String description;

    public Kontobewegung(double betrag, LocalDate datum, String description, Konto myKonto) {
        this.betrag = betrag;
        this.datum = datum;
        this.description = description;
        this.myKonto = myKonto;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBetrag() {
        return this.betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public LocalDate getDatum() {
        return this.datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Konto getMyKonto() {
        return this.myKonto;
    }

    public void setMyKonto(Konto myKonto) {
        this.myKonto = myKonto;
    }
}
