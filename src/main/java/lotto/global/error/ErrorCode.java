package lotto.global.error;

public enum ErrorCode {
    NULL_INPUT("[ERROR] 입력이 null 입니다."),
    EMPTY_INPUT("[ERROR] 입력이 비어 있습니다."),
    NOT_INTEGER("[ERROR] 숫자를 입력해 주세요."),
    INVALID_PURCHASE("[ERROR] 구입 금액은 1,000원 단위의 양의 정수여야 합니다.");


    private final String message;
    ErrorCode(String message) { this.message = message; }
    public String message() { return message; }
}
