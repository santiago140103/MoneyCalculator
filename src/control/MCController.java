package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import persistence.rest.ExchangeRateLoaderFromWebService;
import view.swing.DialogSwing;

public class MCController implements ActionListener {
    private final DialogSwing dialogSwing;
    private final ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService;

    public MCController(DialogSwing dialogSwing,
                        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService) {
        this.dialogSwing = dialogSwing;
        this.exchangeRateLoaderFromWebService = exchangeRateLoaderFromWebService;
        this.dialogSwing.registerController(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       Money money = (Money) this.dialogSwing.getMoney();
       Currency currencyFrom = money.getCurrency();
       Currency currencyTo = (Currency) this.dialogSwing.getCurrencyTo();
       ExchangeRate exchangeRate = this.exchangeRateLoaderFromWebService.exchangerateLoader(currencyFrom, currencyTo);
       
       this.dialogSwing.refreshMoney(new Money(exchangeRate.getRate() * money.getAmount(), currencyTo));
    }
}
