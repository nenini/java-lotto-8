package lotto.controller;

import lotto.domain.Lotto;
import lotto.global.error.AppException;
import lotto.service.LottoMachine;
import lotto.util.Parser;
import lotto.util.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;


public class LottoController {
    LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        int purchase = purchaseWithRetry();
        List<Lotto> lottoBundle=lottoMachine.createLottoBundle(purchase);

        OutputView.printPurchasedCount(lottoBundle.size());
        for(Lotto lotto:lottoBundle){
            OutputView.printLottoTicket(lotto);
        }


    }

    private int purchaseWithRetry() {
        while (true) {
            try {
                OutputView.askPurchase();
                int purchase = Parser.parseInt(InputView.readPurchase());
                Validator.requiredPurchase(purchase);
                return purchase;
            } catch (IllegalStateException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

}
