package lotto.global.message;

public enum ViewMessages {
    ASK_PURCHASE("구입금액을 입력해 주세요."),
    ASK_WINNING("당첨 번호를 입력해 주세요."),
    ASK_BONUS("보너스 번호를 입력해 주세요."),

    PURCHASED_COUNT("%d개를 구매했습니다."),

    RESULT_HEADER("당첨 통계"),
    RESULT_DIVIDER("---"),

    TOTAL_YIELD("총 수익률은 %s입니다.");


    private final String message;

    ViewMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
