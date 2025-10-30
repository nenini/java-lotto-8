package lotto.view;

import lotto.domain.Lotto;
import lotto.global.message.StatLine;
import lotto.global.message.ViewMessages;

import java.util.List;

public class OutputView {
    public static void askPurchase() {
        System.out.println(ViewMessages.ASK_PURCHASE.getMessage());
    }

    public static void askWinning() {
        System.out.println();
        System.out.println(ViewMessages.ASK_WINNING.getMessage());
    }

    public static void askBonus() {
        System.out.println();
        System.out.println(ViewMessages.ASK_BONUS.getMessage());
    }

    public static void printPurchasedCount(int count) {
        System.out.println();
        System.out.println(ViewMessages.PURCHASED_COUNT.format(count));
    }

    public static void printLottoTicket(Lotto lotto) {
        System.out.println(lotto.getNumbers().toString());
    }

    public static void printResultHeader() {
        System.out.println();
        System.out.println(ViewMessages.RESULT_HEADER.getMessage());
        System.out.println(ViewMessages.RESULT_DIVIDER.getMessage());
    }

    public static void printStatLine(StatLine line, int count) {
        System.out.println(line.format(count));
    }

    public static void printTotalYield(String percentText) {
        System.out.println(ViewMessages.TOTAL_YIELD.format(percentText));
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
