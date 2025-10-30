package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.WinningLottoNumber;
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
        List<Lotto> lottoBundle = lottoMachine.createLottoBundle(purchase);

        OutputView.printPurchasedCount(lottoBundle.size());
        for (Lotto lotto : lottoBundle) {
            OutputView.printLottoTicket(lotto);
        }
        WinningLottoNumber winningLottoNumber = winningWithRetry();
        //TODO : 당첨 판정


    }

    private int purchaseWithRetry() {
        while (true) {
            try {
                OutputView.askPurchase();
                int purchase = Parser.parseInt(InputView.readPurchase());
                Validator.requiredPurchase(purchase);
                return purchase;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private WinningLottoNumber winningWithRetry() {
        final List<Integer> winningNumbers = winningNumbersWhitRetry();
        final int winningBonus = winningBonusWhitRetry(winningNumbers);
        return new WinningLottoNumber(winningNumbers, winningBonus);
    }

    private List<Integer> winningNumbersWhitRetry() {
        while (true) {
            try {
                OutputView.askWinning();
                List<Integer> winningNumbers = Parser.parseIntList(InputView.readWinning());
                WinningLottoNumber.validateNumbers(winningNumbers);
                return winningNumbers;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private int winningBonusWhitRetry(List<Integer> winningNumbers) {
        while (true) {
            try {
                OutputView.askBonus();
                int bonus = Parser.parseInt(InputView.readBonus());
                WinningLottoNumber.validateBonus(winningNumbers, bonus);
                return bonus;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

}
