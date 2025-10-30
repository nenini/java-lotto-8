package lotto.global.error;

public class AppException {
    private AppException() {}

    public static IllegalArgumentException of(ErrorCode code) {
        return new IllegalArgumentException(code.message());
    }

    public static void throwError(ErrorCode code) {
        throw of(code);
    }
}
