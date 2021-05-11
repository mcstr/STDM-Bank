import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws ParseException {
        Kunde kunde1 = new Kunde(1, "Paul Müller", "Münster", LocalDate.of(2021, 2, 10), null);
        Kunde kunde2 = new Kunde(2, "Liese Fleißig", "Osnabrück", LocalDate.of(2018, 5, 2), null);
        Girokonto giro1 = new Girokonto("50060080", 1.5D, 0.0D, null, null, 7.5D, 3000.0D, LocalDate.of(2021, 2, 10));
        giro1.eroeffnen(kunde1, 1.0D);
        giro1.eroeffnen(kunde2, 1.0D);
        Girokonto giro2 = new Girokonto("50060090", 1.5D, 0.0D,null, null, 7.5D, 2000.0D, LocalDate.of(2021, 1, 2));
        giro2.eroeffnen(kunde2, 1.0D);
        giro1.abheben(8500.0D, LocalDate.of(2021, 2, 11));
        Sparkonto spar1 = new Sparkonto("22222222", 5.0D, 0.0D, null, null, 'p',
                LocalDate.of(2021, 1, 2));
        spar1.eroeffnen(kunde2, 1.0D);
        spar1.einzahlen(2850.0D, LocalDate.of(2021, 2, 11));
        Bank.myKunden.forEach((kunde) -> {
            kunde.printKundeKontostand();
        });
    }

    public static String format(LocalDate datum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        return datum.format(formatter);
    }

    public static String formatNumber(double number) {
        DecimalFormatSymbols symbolsDE_DE = DecimalFormatSymbols.getInstance(Locale.GERMANY);
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00", symbolsDE_DE);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        return decimalFormat.format(number);
    }
}
