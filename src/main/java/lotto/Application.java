package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OutputView.askPurchase();
        InputView.readPurchase();

        //TODO: 구매 로또 번호 출력

        OutputView.askWinning();
        InputView.readWinning();

        OutputView.askBonus();
        InputView.readBonus();

        OutputView.printResultHeader();
        //TODO: StatLine 출력

    }
}
