package lotto.controller;

import lotto.global.error.AppException;
import lotto.util.Parser;
import lotto.util.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run(){
        int purchase = purchaseWithRetry();
    }

    private int purchaseWithRetry(){
        while(true){
            try{
                OutputView.askPurchase();
                int purchase= Parser.parseInt(InputView.readPurchase());
                Validator.requiredPurchase(purchase);
                return purchase;
            }catch(IllegalStateException e){
                OutputView.printError(e.getMessage());
            }
        }
    }
}
