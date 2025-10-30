package lotto.global.error;

public enum ErrorCode {
    NULL_INPUT("[ERROR] 입력이 null 입니다."),
    EMPTY_INPUT("[ERROR] 입력이 비어 있습니다."),
    NOT_INTEGER("[ERROR] 숫자를 입력해 주세요."),
    INVALID_PURCHASE("[ERROR] 구입 금액은 1,000원 단위의 양의 정수여야 합니다."),
    NOT_SIX_NUMBERS("[ERROR] 로또 번호는 6개여야 합니다."),
    OUT_OF_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_NUMBER("[ERROR] 로또 번호는 중복될 수 없습니다."),
    BONUS_CONFLICT("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");


    private final String message;
    ErrorCode(String message) { this.message = message; }
    public String message() { return message; }
}
