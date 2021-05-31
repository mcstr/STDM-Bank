
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Kunde {
    private String kundenNummer;
    private String name;
    private String addresse;
    private LocalDate kundenSeit;
    private ArrayList<Konto> myKonten;

    public Kunde(String kundenNummer, String name, String addresse, LocalDate kundenSeit, ArrayList<Konto> myKonten) {
        this.kundenNummer = kundenNummer;
        this.name = name;
        this.addresse = addresse;
        this.myKonten = myKonten;
        this.kundenSeit = kundenSeit;
        Bank.addKunde(this);
    }

    public void addKonto(Konto konto) {
        if (this.myKonten == null) {
            this.myKonten = new ArrayList<Konto>();
            this.myKonten.add(konto);
        } else {
            this.myKonten.add(konto);
        }

    }

    public void printKontoInfo() {
        String inhaber = String.format("Kontoinhaber: %s", this.name);
        System.out.println(inhaber);
        for (Konto konto : this.myKonten) {
            String message = String.format(Locale.GERMANY,
                    "     Kto-Nr.: %s, \r\n     BLZ: %s, %s, \r\n     Kontostand: %,1.2f Euro\r\n",
                    konto.kontoNummer, Bank.blz, Bank.institutsName, konto.kontoStand);
            System.out.println(message);
        };
    }

    public void printKundeKontostand() {
        for (Konto konto : this.myKonten) {
            String message = String.format(Locale.GERMANY,
                    "Kontoauszug \r\n     Kto-Nr.: %s, \r\n     BLZ: %s, %s, \r\n     Kontostand: %,9.2f Euro \r\n     Kontoinhaber: %s\r\n",
                    konto.kontoNummer, Bank.blz, Bank.institutsName, konto.kontoStand, konto.myKunde.getName());
            System.out.println(message);

            for (int i = 0; i < konto.myBewegungen.size(); ++i) {
                Kontobewegung beweg = (Kontobewegung) konto.myBewegungen.get(i);
                String betrag = String.format(Locale.GERMANY, "%,9.2f Euro ", beweg.getBetrag());
                String datum = Main.format(beweg.getDatum());
                String type = beweg.getDescription();
                System.out.println(i + 1 + "  " + datum + "     " + betrag + "     " + type);
            }
            System.out.println("\r\n");

        };
    }

    public String getKundenNummer() {
        return this.kundenNummer;
    }

    public void setKundenNummer(String kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresse() {
        return this.addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public LocalDate getKundenSeit() {
        return this.kundenSeit;
    }

    public void setKundenSeit(LocalDate kundenSeit) {
        this.kundenSeit = kundenSeit;
    }

    public ArrayList<Konto> getMyKonten() {
        return this.myKonten;
    }
}
