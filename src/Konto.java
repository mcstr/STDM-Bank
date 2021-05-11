

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

abstract class Konto {
    protected String kontoNummer;
    protected double habenZins;
    protected double kontoStand;
    protected Kunde myKunde;
    protected ArrayList<Kontobewegung> myBewegungen;
    protected LocalDate kontoeroeffnung;

    public Konto(String kontoNummer, double habenZins, double kontoStand, Kunde myKunde,
            ArrayList<Kontobewegung> myBewegungen, LocalDate kontoeroeffnung) {
        this.habenZins = habenZins;
        this.kontoNummer = kontoNummer;
        this.kontoStand = kontoStand;
        this.myBewegungen = myBewegungen;
        this.myKunde = myKunde;
        this.kontoeroeffnung = kontoeroeffnung;
        Bank.addKonto(this);
    }

    public void eroeffnen(Kunde kunde, double betrag) {
        if (this.myKunde != null) {
            String message = "Konto schon vorhanden \r\n";
            System.err.print(message);
        } else {
            this.myKunde = kunde;
            this.myKunde.addKonto(this);
            this.einzahlen(betrag, this.kontoeroeffnung);
        }

    }

    public void einzahlen(double betrag, LocalDate datum) {
        if (this.myBewegungen == null) {
            this.addKontoBewegung(betrag, datum, "Ersteinzahlung");
        } else {
            this.addKontoBewegung(betrag, datum, "Einzahlung");
        }

        double newkontoStand = this.kontoStand + betrag;
        this.kontoStand = newkontoStand;
    }

    public void abheben(double betrag, LocalDate datum) {
        this.kontoStand -= betrag;
        this.addKontoBewegung(betrag, datum, "Auszalung");
    }

    public void addKontoBewegung(double betrag, LocalDate datum, String description) {
        if (this.myBewegungen == null) {
            this.myBewegungen = new ArrayList<Kontobewegung>();
        }

        Kontobewegung newKontobewegung = new Kontobewegung(betrag, datum, description, this);
        this.myBewegungen.add(newKontobewegung);
    }

    public void printKontoInfo() {
        String message = String.format(Locale.GERMANY,
                "Kontoinhaber: %s \r\n     Kto-Nr.: %s, \r\n     BLZ: %s, %s \r\n     Kontostand: %,.2f Euro\r\n",
                this.myKunde.getName(), this.kontoNummer, Bank.blz, Bank.institutsName, this.kontoStand);
        System.out.println(message);
    }

    public void print

    public abstract void zinsenBerechnen();
}
