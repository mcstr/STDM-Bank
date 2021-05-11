
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Kunde {
    private int kundenNummer;
    private String name;
    private String addresse;
    private LocalDate kundenSeit;
    private ArrayList<Konto> myKonten;

    public Kunde(int kundenNummer, String name, String addresse, LocalDate kundenSeit, ArrayList<Konto> myKonten) {
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
        this.myKonten.forEach((konto) -> {
            String message = String.format(Locale.GERMANY,
                    "Kontoinhaber: %s \r\n     Kto-Nr.: %s, \r\n     BLZ: %s, %s, \r\n     Kontostand: %,.2f Euro\r\n",
                    konto.myKunde.getName(), konto.kontoNummer, Bank.blz, Bank.institutsName, konto.kontoStand);
            System.out.println(message);
        });
    }

    public void printKundeKontostand() {
        this.myKonten.forEach((konto) -> {
            String message = String.format(Locale.GERMANY,
                    "Kontoauszug \r\n     Kto-Nr.: %s, \r\n     BLZ: %s, %s, \r\n     Kontostand: %,.2f Euro \r\n     Kontoinhaber: %s \r\n",
                    konto.kontoNummer, Bank.blz, Bank.institutsName, konto.kontoStand, konto.myKunde.getName());
            System.out.println(message);

            for (int i = 0; i < konto.myBewegungen.size(); ++i) {
                Kontobewegung beweg = (Kontobewegung) konto.myBewegungen.get(i);
                String betrag = String.format(Locale.GERMANY, "%,9.2f Euro ", beweg.getBetrag());
                String datum = Main.format(beweg.getDatum());
                String type = beweg.getDescription();
                System.out.println(i + 1 + "  " + datum + "     " + betrag + "     " + type);
            }

        });
    }

    public int getKundenNummer() {
        return this.kundenNummer;
    }

    public void setKundenNummer(int kundenNummer) {
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
