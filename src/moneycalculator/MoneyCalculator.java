package moneycalculator;

import java.util.List;
import javax.swing.SwingUtilities;
import control.MCController;
import model.Currency;
import model.Money;
import persistence.files.CurrencyLoaderFromFile;
import persistence.rest.ExchangeRateLoaderFromWebService;
import view.swing.DialogSwing;
import view.swing.MoneyCalculatorGUISwing;

public class MoneyCalculator {

    public static void main(String[] args) {
        System.out.println("MoneyCalculator...");
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("Curren.txt");
        List<Currency> currencies = currencyLoaderFromFile.currencyLoader();
        
        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService();

        DialogSwing dialogSwing = new DialogSwing(currencies, new Money(0.0, currencies.get(0)));
        
        new MCController(dialogSwing, 
                         exchangeRateLoaderFromWebService);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoneyCalculatorGUISwing(dialogSwing, "Money Calculator Display...");        
            }
        });
    }
}
