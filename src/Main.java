import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws ParseException {
        Kunde kunde1 = new Kunde("1", "Paul Müller", "Münster", LocalDate.of(2021, 2, 10), null);
        Kunde kunde2 = new Kunde("2", "Lieschen Fleißig", "Osnabrück", LocalDate.of(2018, 5, 2), null);



        // Spar1

        Sparkonto spar1 = new Sparkonto("22222222", 5.0D, 0.0D, null, null, 'p', LocalDate.of(2021, 1, 2));
        spar1.eroeffnen(kunde2, 1.0D);
        spar1.einzahlen(2850.0D, LocalDate.of(2021, 2, 11));
        spar1.zinsenBerechnen(LocalDate.of(2022, 1, 01));

        // Giro 1

        Girokonto giro1 = new Girokonto("50060080", 1.5D, 0.0D, null, null, 7.5D, 3000.0D, LocalDate.of(2021, 2, 10));
        giro1.eroeffnen(kunde1, 1.0D);
        giro1.abheben(8500.0D, LocalDate.of(2021, 2, 11));
        giro1.zinsenBerechnen(LocalDate.of(2021, 4, 01));
        giro1.zinsenBerechnen(LocalDate.of(2021, 7, 01));
        giro1.zinsenBerechnen(LocalDate.of(2021, 10, 01));
        giro1.zinsenBerechnen(LocalDate.of(2022, 01, 01));


        // Giro 2

        Girokonto giro2 = new Girokonto("50060090", 1.5D, 0.0D,null, null, 7.5D, 2000.0D, LocalDate.of(2021, 1, 2));
        giro2.eroeffnen(kunde2, 1.0D);
        giro2.abheben(100.5D, LocalDate.of(2021, 2, 11));
        giro2.abheben(50, LocalDate.of(2021, 2, 11));
        giro2.zinsenBerechnen(LocalDate.of(2021, 4, 01));
        giro2.zinsenBerechnen(LocalDate.of(2021, 7, 01));
        giro2.zinsenBerechnen(LocalDate.of(2021, 10, 01));
        giro2.zinsenBerechnen(LocalDate.of(2022, 01, 01));


        // Ausgabe
        for (Kunde kunde : Bank.myKunden) {
            kunde.printKontoInfo();
        }

        spar1.printKontoStand();
        giro1.printKontoStand();
        giro2.printKontoStand();
    }

    public static String format(LocalDate datum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        return datum.format(formatter);
    }

}
