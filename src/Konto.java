

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

    protected void eroeffnen(Kunde kunde, double betrag) {
        if (this.myKunde != null) {
            String message = "Konto schon vorhanden \r\n";
            System.err.print(message);
        } else {
            this.myKunde = kunde;
            this.myKunde.addKonto(this);
            this.einzahlen(betrag, this.kontoeroeffnung);
        }

    }

    protected void einzahlen(double betrag, LocalDate datum) {
        double newkontoStand = this.kontoStand + betrag;
        this.kontoStand = newkontoStand;
        if (this.myBewegungen == null) {
            this.addKontoBewegung(betrag, datum, "Ersteinzahlung", this.kontoStand);
        } else {
            this.addKontoBewegung(betrag, datum, "Einzahlung", this.kontoStand);
        }


    }

    protected void abheben(double betrag, LocalDate datum) {
        double newkontoStand = this.kontoStand - betrag;
        this.kontoStand = newkontoStand;
        this.addKontoBewegung(-(betrag), datum, "Auszalung", this.kontoStand);
    }

    protected void addZinsen(double betrag, LocalDate datum) {
        double newkontoStand = this.kontoStand + betrag;
        this.kontoStand = newkontoStand;
        this.addKontoBewegung(betrag, datum, "Zinsen", this.kontoStand);
    }

    protected void addKontoBewegung(double betrag, LocalDate datum, String description, double kontoStand) {
        if (this.myBewegungen == null) {
            this.myBewegungen = new ArrayList<Kontobewegung>();
        }

        Kontobewegung newKontobewegung = new Kontobewegung(betrag, datum, description, this, kontoStand);
        this.myBewegungen.add(newKontobewegung);
    }

    protected void printKontoInfo() {
        String message = String.format(Locale.GERMANY,
                "Kontoinhaber: %s \r\n     Kto-Nr.: %s, \r\n     BLZ: %s, %s \r\n     Kontostand: %,.2f Euro\r\n",
                this.myKunde.getName(), this.kontoNummer, Bank.blz, Bank.institutsName, this.kontoStand);
        System.out.println(message);
    }

    protected void printKontoStand() {
        String message = String.format(Locale.GERMANY,
                "Kontoauszug \r\n     Kto-Nr.: %s, \r\n     BLZ: %s, %s, \r\n     Kontostand: %,9.2f Euro \r\n     Kontoinhaber: %s\r\n",
                this.kontoNummer, Bank.blz, Bank.institutsName, this.kontoStand, this.myKunde.getName());
        System.out.println(message);

        for (int i = 0; i < this.myBewegungen.size(); ++i) {
            Kontobewegung beweg = (Kontobewegung) this.myBewegungen.get(i);
            String betrag = String.format(Locale.GERMANY, "%,9.2f Euro ", beweg.getBetrag());
            String datum = Main.format(beweg.getDatum());
            String type = beweg.getDescription();
            System.out.println(i + 1 + "  " + datum + "     " + betrag + "     " + type);
        }
        System.out.println("\r\n");
    }

    protected abstract void zinsenBerechnen(LocalDate datum);
}
