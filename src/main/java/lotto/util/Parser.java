package lotto.util;

import lotto.global.error.AppException;
import lotto.global.error.ErrorCode;

public class Parser {

    private Parser() {
    }

    public static int parseInt(String rawInput) {
        checkInputNotNull(rawInput);
        String trimmedRawInput = rawInput.trim();
        checkInputNotEmpty(trimmedRawInput);

        try {
            return Integer.parseInt(trimmedRawInput);
        } catch (NumberFormatException e) {
            AppException.throwError(ErrorCode.NOT_INTEGER);
        }

        return 0;
    }

    private static void checkInputNotNull(String rawInput) {
        if (rawInput == null) {
            AppException.throwError(ErrorCode.NULL_INPUT);
        }
    }

    private static void checkInputNotEmpty(String input) {
        if (input.isEmpty()) {
            AppException.throwError(ErrorCode.EMPTY_INPUT);
        }
    }
}
