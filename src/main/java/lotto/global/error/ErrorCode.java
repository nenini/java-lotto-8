package lotto.global.error;

public enum ErrorCode {
    NULL_INPUT("[ERROR] 입력이 null 입니다."),
    EMPTY_INPUT("[ERROR] 입력이 비어 있습니다.");


    private final String message;
    ErrorCode(String message) { this.message = message; }
    public String message() { return message; }
}
